package org.kingsski.kaas.service.division;

import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.division.Division;
import org.kingsski.kaas.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Defines the API for querying {@link Division}s
 */
@RestController
public class DivisionRestController {

    private final DivisionService divisionService;
    private final CompetitionService competitionService;

    public DivisionRestController(DivisionService divisionService,
                                  CompetitionService competitionService) {
        this.divisionService = divisionService;
        this.competitionService = competitionService;
    }

    @GetMapping(
            path = "/division",
            produces = "application/json"
    )
    public ResponseEntity divisions(@RequestParam(name = "competition", required = false) String competition,
                                  @RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return divisionByName(name);
        } else if (competition != null && !competition.isEmpty()) {
            return divisionsByCompetition(competition);
        }

        return ResponseEntity.ok(divisionService.getDivisions());
    }

    @GetMapping(
            path = "/division/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity divisionById(@PathVariable("id") long id) {
        final Division division = divisionService.getDivisionById(id);
        if (division == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(division);
    }

    private ResponseEntity divisionsByCompetition(String competitionName) {
        final Competition competition = competitionService.getCompetitionByName(competitionName);

        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final List<Division> divisions = divisionService.getDivisionsByCompetition(competitionName);

        return ResponseEntity.ok(divisions);
    }

    private ResponseEntity divisionByName(String name) {
        final Division division = divisionService.getDivisionByName(name);
        if (division == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(division);
    }
}
