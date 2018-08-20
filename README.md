# kings-race-service

Master:  
[![Master Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=master)](https://travis-ci.org/andrewflbarnes/kings-results-service) 
[![Coverage Status](https://coveralls.io/repos/github/andrewflbarnes/kings-results-service/badge.svg?branch=master)](https://coveralls.io/github/andrewflbarnes/kings-results-service?branch=master)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=org.kingsski:kings-race-service)](https://sonarcloud.io/dashboard?id=org.kingsski:kings-race-service)

Modules:
- data (lib): contains entity models
- database (lib): contains DAO interfaces and implementations
- service-query (app): contains the querying services and API controllers

Kings web API implemented as REST server which returns:
- in progress and historical Kings races
- current and historical team seedings
- individual runs

Endpoints are:
- `/races`  
Returns races for all leagues for the most recent season and round
- `/races/league`  
Returns races for the specific league for the most recent season and round
- `/races/league/round`  
Returns races for the specific league and round for the most recent season
- `/races/season/league/round`  
Returns races for the specific season, league and round
- `/teams`  
Returns teams and scores for all leagues for the most recent season and all divisions
- `/teams/league`  
Returns teams and scores for the specific league for the most recent season and all divisions
- `/teams/league/division`  
Returns teams and scores for the specific league and division for the most recent season
- `/teams/season/league/division`  
Returns teams and scores for the specific season, league and division
- `/individuals`  
Returns all individuals and times
- `/individuals/discipline`  
Returns individuals and times for the specific discipline

Starting the server:  
`java -jar kings-race-service.jar --spring.profiles.active=profile`

Available profiles:
- `jdbc`  
JDBC services return results from the specified DB
- `mysql`  
Uses a MySQL database for JDBC services
- `caching`  
Service results are cached to reduce system load
- `nocaching`  
Service results are not cached to ensure results are up to date

Available test profiles:
- `dummy`  
Dummy services return the same (or similar) objects for all paths
- `stub`  
Stub services return an empty object list for all paths