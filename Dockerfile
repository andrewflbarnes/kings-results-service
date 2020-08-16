FROM maven:3-jdk-8-slim as builder
WORKDIR /build
RUN mkdir -p database/database-{access,scripts} jacoco-report results-service
COPY pom.xml                           .
RUN mvn clean install -Dmaven.test.skip -Ppostgres -N
COPY database/pom.xml                  database/.
RUN mvn clean install -Dmaven.test.skip -Ppostgres -f database -N
COPY database/database-scripts/pom.xml database/database-scripts/.
RUN mvn clean install -Dmaven.test.skip -Ppostgres -f database/database-scripts -N
RUN mkdir -p database/database-access/src/test/resources
COPY database/database-access/pom.xml  database/database-access/.
RUN touch database/database-access/src/test/resources/test.properties
RUN mvn clean install -Dmaven.test.skip -Ppostgres -f database/database-access -N
COPY results-service/pom.xml           results-service/.
# Fails right at the end on spring boot repackage
RUN mvn clean install -Dmaven.test.skip -Ppostgres -f results-service -N -fn
COPY ./ .
RUN mvn clean install -Dmaven.test.skip -Ppostgres

FROM openjdk:8-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY --from=builder /build/results-service/target/results-service*.jar results-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "results-service.jar"]
