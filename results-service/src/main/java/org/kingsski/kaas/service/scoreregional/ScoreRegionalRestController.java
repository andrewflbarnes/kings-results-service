package org.kingsski.kaas.service.scoreregional;

import org.kingsski.kaas.database.team.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines the API for querying {@link Team}s
 */
@RestController
public class ScoreRegionalRestController {

    private ScoreRegionalService scoreRegionalService;

    public ScoreRegionalRestController(ScoreRegionalService scoreRegionalService) {
        this.scoreRegionalService = scoreRegionalService;
    }

    @GetMapping(
            path = "/score/regional",
            produces = "application/json"
    )
    public ResponseEntity scoreRegionals() {
        return ResponseEntity.ok(scoreRegionalService.getScoreRegionals());
    }
}
