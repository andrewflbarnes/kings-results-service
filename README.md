# kings-race-service

Master:  
[![Master Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=master)](https://travis-ci.org/andrewflbarnes/kings-results-service) 
[![Coverage Status](https://img.shields.io/coveralls/github/andrewflbarnes/kings-results-service.svg)](https://coveralls.io/github/andrewflbarnes/kings-results-service?branch=master)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.kingsski.kaas%3Akaas-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.kingsski.kaas:kaas-parent)

Modules:
- database (lib): contains SQL scripts for generating the DBs
- database-access (lib): contains DB models, DAO interfaces and implementations which provide these
- service-query (app): contains the querying services and API controllers as a Spring Boot application

Kings web API implemented as REST server which returns:
- clubs

Endpoints are:
- `GET  /clubs`  
Returns all clubs
- `GET  /club/{id}`  
Returns a club by its ID
- `GET  /club/{name}`  
Returns a club by its name

Starting the server:  
`java -jar kings-race-service-query.jar`

Profiles can be set in application.properties
Available profiles:
- `jdbc`  
Uses JDBC implementations of DAOs - configure them in database.properties
- `dbcp2`  
Uses DBCP2 for connection pooling
