#!/usr/bin/env bash

function create_machine() {
 machine=$1
 if ! docker-machine status ${machine} &>/dev/null ; then
   echo "Creating ${machine}"
   docker-machine create --driver virtualbox ${machine}
 else
   echo "Docker machine ${machine} already exists"
 fi
}

function leave_if_swarmed() {

  stack=$1

  if ! docker node ls &>/dev/null ; then
    return
  fi

  role=$(docker node inspect self -f '{{ .Spec.Role }}')

  if [[ "x${role}" = "xmanager" ]] ; then
    echo "Leaving swarm"

    if docker stack rm ${stack} &>/dev/null ; then
      echo "Stack ${stack} removed"
    fi

    docker swarm leave --force &>/dev/null
  fi

}

function machine_ip() {
  docker-machine inspect $1 -f '{{ .Driver.IPAddress }}'
}

function build_container() {
  docker-compose build >/dev/null
}

function disconnect_docker() {
  eval $(docker-machine env -u)
}

function connect_docker() {
  eval $(docker-machine env ${1})
}

function mvn_build() {
  echo "Building project"

  disconnect_docker
  docker-compose up -d ${db_server}
  sleep 5

  mvn package -Dmaven.test.skip -Ddb.type=postgres 1>/dev/null

  docker-compose stop ${db_server}
}

function on_trap() {
  disconnect_docker
  echo "If the script failed on provisioning docker machines because of connectivity"
  echo "issues, it may suffice to just rerun the script"
}

function help_text() {
  echo "Syntax:"
  echo "${0} [OPTIONS]"
  echo
  echo "Options (1 of):"
  echo "- help    : display this help text"
  echo "- nobuild : don't perform the maven build"
  echo
  echo "This script is intended to be an example of how to configure a docker swarm and"
  echo "deploy service across the nodes within it."
  echo
  echo "The script will perform the below actions (assuming a fresh system)"
  echo "- Build the maven project skipping tests"
  echo "- Use docker-machine to create a master node called dev-master"
  echo "- Start a swarm on the master node"
  echo "- Use docker-machine to create several worker nodes called dev-worker-n"
  echo "- Connect each worker node to the swarm"
  echo "- Build the kaas/results-service:latest image in each node"
  echo "- Deploy a stack called \"kaas\" as defined in docker-compose.yml to the swarm"
  echo "- Scale the results-service service in the kaas stack to 10 instances"
  echo "- Provision the backend database with test data"
  echo
  echo "In terms of commands being called this roughly equates to the below - please"
  echo "note that these commands are not complete and just provide the general gist"
  echo "- mvn package -Dmaven.test.skip"
  echo "- docker-machine create --driver=virtualbox dev-master"
  echo "- eval \$(docker-machine env dev-master)"
  echo "- docker swarm init"
  echo "- docker-compose build"
  echo "- for each worker:"
  echo "-- docker-machine create --driver=virtualbox dev-worker-n"
  echo "-- eval \$(docker-machine env dev-worker-n)"
  echo "-- docker swarm join"
  echo "-- docker-compose build"
  echo "- eval \$(docker-machine env dev-master)"
  echo "- docker stack deploy -c docker-compose.yml kaas"
  echo "- docker service scale kaas_results-service=10"
  echo "- mvn -Pflyway-migrate"
  echo "- mvn -Pflyway-load-test"
  echo
  echo "Configuration is then displayed which can be used to start an haproxy instance"
  echo "and an example curl command is given"
  echo
  echo "For clarity, read though this script, docker-compose.yml and the Dockerfile"
}

[[ "x${1}" = "xhelp" ]] && help_text && exit 0

set -e

trap on_trap SIGINT SIGTERM

master_node=dev-master
workers=1
services=6
worker_node_prefix=dev-worker
swarm_port=2377
stack_name=kaas
db_server=postgres10

# Load docker compose variables
. .env
# results_service_port
# db_port

db_service=${stack_name}_${db_server}


disconnect_docker

if [[ "x${1}" != "xnobuild" ]] ; then
  mvn_build
fi



if docker-machine status ${master_node} ; then
  echo "Leaving swarm on master node ${master_node}"
  connect_docker ${master_node}
  leave_if_swarmed ${stack_name}
else
  echo "Create master node ${master_node}"
  create_machine ${master_node}
  connect_docker ${master_node}
fi

master_ip=$(machine_ip ${master_node})



echo "Starting swarm on master node ${master_node}"

docker swarm init --advertise-addr ${master_ip} 1>/dev/null
master_token=$(docker swarm join-token worker -q)

echo "Build app image on master node ${master_node}"

docker-compose build >/dev/null



echo "Create ${workers} worker node(s)"

unset worker_ips

i=0
while [[ ${i} < ${workers} ]]; do
  i=$((i+1))
  worker_node=${worker_node_prefix}-${i}

  echo "Create worker node ${worker_node}"

  create_machine ${worker_node}

  sleep 5

  connect_docker ${worker_node}
  if docker swarm leave &>/dev/null ; then
    echo "Worker node left previous swarm ${worker_node}"
  fi

  echo "Join swarm from worker node ${worker_node}"
  docker swarm join --token ${master_token} ${master_ip}:${swarm_port}
  worker_ips="${worker_ips} $(machine_ip ${worker_node})"

  echo "Build app image on worker node ${worker_node}"

  docker-compose build >/dev/null
done



echo "Start service on the swarm"

connect_docker ${master_node}

env $(cat .env | grep ^[a-zA-Z] | xargs) docker stack deploy -c docker-compose.yml ${stack_name}
docker service ls

echo "Scale to ${services} services"

docker service scale ${stack_name}_results-service=${services}
# docker service ls
# docker stack ps ${stack_name}



echo "Provision database"

wait=2

while [[  $(docker service ps ${db_service} --format '{{ .CurrentState }}') != "Running"* ]] ; do
  echo "Postgres not yet running, waiting ${wait}s"
  sleep ${wait}
done

while [[  $(docker service ls --filter "name=${db_service}" --format '{{ .Replicas }}') != "1/1" ]] ; do
  echo "Postgres not yet replicated, waiting ${wait}s"
  sleep ${wait}
done

mvn -Pdb-migrate -Ddb.port=${db_port} -Ddb.host=${master_ip} -pl database/database-scripts >/dev/null
mvn -Pdb-load-test -Ddb.port=${db_port} -Ddb.host=${master_ip} -pl database/database-scripts >/dev/null

disconnect_docker



echo "Add the below config to haproxy.cfg and run haproxy -f haproxy.cfg"

echo " server kaas1 ${master_ip}:${results_service_port} check"

i=2
for ip in ${worker_ips}; do
  echo " server kaas${i} ${ip}:${results_service_port} check"
  i=$((i+1))
done



echo "Test the endpoint curl localhost:9000/clubs"
