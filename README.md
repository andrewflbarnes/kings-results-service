# kings-results-service

Master:  
[![Master Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=master)](https://travis-ci.org/andrewflbarnes/kings-results-service) 
[![codecov](https://codecov.io/gh/andrewflbarnes/kings-results-service/branch/master/graph/badge.svg)](https://codecov.io/gh/andrewflbarnes/kings-results-service)

Develop:  
[![Develop Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=develop)](https://travis-ci.org/andrewflbarnes/kings-results-service) 
[![codecov](https://codecov.io/gh/andrewflbarnes/kings-results-service/branch/develop/graph/badge.svg)](https://codecov.io/gh/andrewflbarnes/kings-results-service)

Kings web API implemented as REST server which returns:
- in progress and historical Kings races
- current and historical team seedings

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

Starting the server:  
`java -jar kings-race-service.jar --spring.profiles.active=profile`

Available profiles:
- `dummy`  
Dummy services return the same (or similar) objects for all paths
- `stub`  
Stub services return an empty object list for all paths
