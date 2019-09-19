package org.kingsski.kaas.service.league;

import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.league.League;
import org.kingsski.kaas.service.competition.CompetitionService;
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

    private final LeagueService leagueService;
    private final CompetitionService competitionService;

    public LeagueRestController(LeagueService leagueService,
                                CompetitionService competitionService) {
        this.leagueService = leagueService;
        this.competitionService = competitionService;
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
        final League league = leagueService.getLeagueById(id);
        if (league == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(league);
    }

    private ResponseEntity leaguesByCompetition(String competitionName) {
        final Competition competition = competitionService.getCompetitionByName(competitionName);

        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final List<League> leagues = leagueService.getLeaguesByCompetition(competitionName);

        return ResponseEntity.ok(leagues);
    }

    private ResponseEntity leagueByName(String name) {
        League league = leagueService.getLeagueByName(name);
        if (league == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(league);
    }
}
