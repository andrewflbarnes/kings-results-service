package org.kingsski.kaas.service.regional;

import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.regional.Regional;
import org.kingsski.kaas.service.competition.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Defines the API for querying {@link Regional}s
 */
@RestController
public class RegionalRestController {

    private final RegionalService regionalService;
    private final CompetitionService competitionService;

    public RegionalRestController(RegionalService regionalService,
                                  CompetitionService competitionService) {
        this.regionalService = regionalService;
        this.competitionService = competitionService;
    }

    @GetMapping(
            path = "/regional",
            produces = "application/json"
    )
    public ResponseEntity regionals(@RequestParam(name = "competition", required = false) String competition) {
        if (competition != null && !competition.isEmpty()) {
            return regionalsByCompetition(competition);
        }

        return ResponseEntity.ok(regionalService.getRegionals());
    }

    @GetMapping(
            path = "/regional/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity regionalById(@PathVariable("id") long id) {
        final Regional regional = regionalService.getRegionalById(id);
        if (regional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(regional);
    }

    private ResponseEntity regionalsByCompetition(String competitionName) {
        final Competition competition = competitionService.getCompetitionByName(competitionName);

        if (competition == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final List<Regional> regionals = regionalService.getRegionalsByCompetition(competitionName);

        return ResponseEntity.ok(regionals);
    }
}
