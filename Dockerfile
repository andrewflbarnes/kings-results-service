FROM openjdk:8-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY ./results-service/target/results-service*.jar results-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "results-service.jar"]
