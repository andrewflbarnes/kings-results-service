package org.kingsski.kaas.service.division;

import org.kingsski.kaas.database.division.Division;
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

    private DivisionService divisionService;

    public DivisionRestController(DivisionService divisionService) {
        this.divisionService = divisionService;
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
        Division division = divisionService.getDivisionById(id);
        if (division == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(divisionService.getDivisionById(id));
    }

    private ResponseEntity divisionsByCompetition(String competition) {
        List<Division> divisions = divisionService.getDivisionsByCompetition(competition);
        if (divisions == null || divisions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(divisions);
    }

    private ResponseEntity divisionByName(String name) {
        Division division = divisionService.getDivisionByName(name);
        if (division == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(divisionService.getDivisionByName(name));
    }
}
