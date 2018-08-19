package org.kingsski.api.query.controller;

import org.kingsski.api.query.service.TeamService;
import org.kingsski.data.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class acts as a REST controller for the Kings Team Service providing
 * Teams as requested. The base URL for requests is "/Teams". All parameters
 * for this API are path based.
 * 
 * This class uses {@link TeamService} to provide the required {@link Team}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping("")
    public ResponseEntity teams() {
        return ControllerUtils.asResponseEntityOk(teamService.getTeamsAll());
    }

    @RequestMapping("{league}")
    public ResponseEntity teamsByLeague(@PathVariable("league") String league) {

        return ControllerUtils.asResponseEntityOk(this.teamService.getTeamsByLeague(league));
    }

    @RequestMapping("{league}/{division}")
    public ResponseEntity teamsByLeagueByDivision(
            @PathVariable("league") String league,
            @PathVariable("division") String division) {

        return ControllerUtils.asResponseEntityOk(this.teamService.getTeamsByLeagueAndDivision(league, division));
    }

    @RequestMapping("{season:[0-9]{4}}/{league}/{division}")
    public ResponseEntity teamsBySeasonByLeagueByDivision(
            @PathVariable("season") String season,
            @PathVariable("league") String league,
            @PathVariable("division") String division) {

        return ControllerUtils.asResponseEntityOk(this.teamService.getTeamsBySeasonAndLeagueAndDivision(season, league, division));
    }
}
