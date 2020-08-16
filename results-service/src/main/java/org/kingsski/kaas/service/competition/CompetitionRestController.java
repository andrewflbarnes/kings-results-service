package org.kingsski.kaas.service.competition;

import org.kingsski.kaas.database.competition.Competition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Defines the API for querying {@link Competition}s
 */
@RestController
public class CompetitionRestController {

    @Resource
    private CompetitionService competitionService;

    @GetMapping(
            path = "/competition",
            produces = "application/json"
    )
    public ResponseEntity competitions() {
        return ResponseEntity.ok(competitionService.getCompetitions());
    }

    @GetMapping(
            path = "/competition/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity competitionById(@PathVariable("id") long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(competition);
    }

    @GetMapping(
            path = "/competition/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity competitionByName(@PathVariable("name") String name) {
        Competition competition = competitionService.getCompetitionByName(name);
        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(competition);
    }
}
