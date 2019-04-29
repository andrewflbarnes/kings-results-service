# kings-race-service
[![Master Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=master)](https://travis-ci.org/andrewflbarnes/kings-results-service) 
[![Coverage Status](https://img.shields.io/coveralls/github/andrewflbarnes/kings-results-service.svg)](https://coveralls.io/github/andrewflbarnes/kings-results-service?branch=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.kingsski.kaas%3Akaas-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.kingsski.kaas:kaas-parent)

### Build
To build the results service run
```bash
mvn clean package -Ppostgres -Dit.test=none
```

### Database
Integration tests and running the server require a database to connect to. You can start one using docker-compose:
```bash
docker-compose up -d postgres10
```
or elsewise in which case you will need to override variables or activate a specific profile

To provision the tables, etc. run the below commands (uses flyway):
```bash
# provision tables and kings data
mvn -Pdb-rebuild -pl database/database-access/
# provision Kings test data
mvn -Pdb-load-test -pl database/database-access/
```

### Starting the server

Ensure that the results-service application has been built using maven and a database is available and provisioned.

##### Starting the server as a local java app:  
```bash
java -jar kings-race-service-query.jar
```
You will need application.properties and database.properties files on the classpath - see those in the docker folder
for an example

##### Starting the server with docker:  
```bash
docker-compose build results-service
docker-compose up -d results-service
```
This will use the properties files in the docker folder (may not work correctly on Windows)

### Modules:
- database (lib): contains SQL scripts for generating the DBs
- database-access (lib): contains DB models, DAO interfaces and implementations which provide these
- results-service (app): contains the querying services and API controllers as a Spring Boot application

### API
Entity view endpoints are:
- `GET  /organisation`  
- `GET  /competition`  
- `GET  /season`  
- `GET  /clubs`  
- `GET  /teams`

Aggregate view endpoints are:
- `GET  /score/regional`

For entity view endpoints the below endpoints are also available
- `GET  /entity/{id}` (get entity by ID)  
- `GET  /entity/{name}` (get entity by name)   

Profiles can be set in application.properties
Available profiles:
- `jdbc` (uses JDBC implementations of DAOs - configure them in database.properties)
- `dbcp2` (uses DBCP2 for connection pooling)
