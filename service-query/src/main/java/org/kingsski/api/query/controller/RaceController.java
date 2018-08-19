package org.kingsski.api.query.controller;

import org.kingsski.data.model.Race;
import org.kingsski.api.query.service.RaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class acts as a REST controller for the Kings Race Service providing
 * races as requested. The base URL for requests is "/races". All parameters
 * for this API are path based.
 * 
 * This class uses {@link RaceService} to provide the required {@link Race}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/races")
public class RaceController {

    private RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @RequestMapping("")
    public ResponseEntity races() {
        return ControllerUtils.asResponseEntityOk(raceService.getRacesAll());
    }

    @RequestMapping("{league}")
    public ResponseEntity racesByLeague(@PathVariable("league") String league) {

        return ControllerUtils.asResponseEntityOk(raceService.getRacesByLeague(league));
    }

    @RequestMapping("{league}/{round}")
    public ResponseEntity racesByLeagueByRound(
            @PathVariable("league") String league,
            @PathVariable("round") String round) {

        return ControllerUtils.asResponseEntityOk(raceService.getRacesByLeagueAndRound(league, round));
    }

    @RequestMapping("{season:[0-9]{4}}/{league}/{round}")
    public ResponseEntity racesBySeasonByLeagueByRound(
            @PathVariable("season") String season,
            @PathVariable("league") String league,
            @PathVariable("round") String round) {

        return ControllerUtils.asResponseEntityOk(
                raceService.getRacesBySeasonAndLeagueAndRound(season, league, round));
    }
}
