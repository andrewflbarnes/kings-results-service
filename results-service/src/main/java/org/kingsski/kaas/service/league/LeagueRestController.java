package org.kingsski.kaas.service.league;

import org.kingsski.kaas.database.league.League;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Defines the API for querying {@link League}s
 */
@RestController
public class LeagueRestController {

    private LeagueService leagueService;

    public LeagueRestController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping(
            path = "/league",
            produces = "application/json"
    )
    public ResponseEntity leagues(@RequestParam(name = "competition", required = false) String competition,
                                  @RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return leagueByName(name);
        } else if (competition != null && !competition.isEmpty()) {
            return leaguesByCompetition(competition);
        }

        return ResponseEntity.ok(leagueService.getLeagues());
    }

    @GetMapping(
            path = "/league/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity leagueById(@PathVariable("id") long id) {
        League league = leagueService.getLeagueById(id);
        if (league == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(leagueService.getLeagueById(id));
    }

    private ResponseEntity leaguesByCompetition(String competition) {
        List<League> leagues = leagueService.getLeaguesByCompetition(competition);
        if (leagues == null || leagues.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(leagues);
    }

    private ResponseEntity leagueByName(String name) {
        League league = leagueService.getLeagueByName(name);
        if (league == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(leagueService.getLeagueByName(name));
    }
}
