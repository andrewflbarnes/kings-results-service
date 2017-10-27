# kings-results-service

Master:  
[![Master Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=master)](https://travis-ci.org/andrewflbarnes/kings-results-service)

Develop:  
[![Develop Build Status](https://travis-ci.org/andrewflbarnes/kings-results-service.svg?branch=develop)](https://travis-ci.org/andrewflbarnes/kings-results-service)

Backend REST server which returns in progress Kings races

Endpoints are:

- `/races`  
Returns races for all leagues for the most recent season and roundName

- `/races/league`  
Returns races for the specific league for the most recent season and roundName

- `/races/league/roundName`  
Returns races for the specific league and roundName for the most recent season

- `/races/season/league/roundName`  
Returns races for the specific season, league and roundName

Starting the server:  
`java -jar kings-race-service.jar --spring.profiles.active=profiles`
