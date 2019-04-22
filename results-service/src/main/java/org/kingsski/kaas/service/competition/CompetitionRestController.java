package org.kingsski.kaas.service.competition;

import org.kingsski.kaas.database.competition.Competition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Defines the API for querying {@link Competition}s
 */
@RestController
public class CompetitionRestController {

    @Resource
    private CompetitionService competitionService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/competitions",
            produces = "application/json"
    )
    public ResponseEntity competitions() {
        return ResponseEntity.ok(competitionService.getCompetitions());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/competition/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity competitionById(@PathVariable("id") long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(competitionService.getCompetitionById(id));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/competition/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity competitionByName(@PathVariable("name") String name) {
        Competition competition = competitionService.getCompetitionByName(name);
        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(competitionService.getCompetitionByName(name));
    }
}
