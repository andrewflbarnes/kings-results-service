package org.kingsski.kaas.service.season;

import org.kingsski.kaas.database.season.Season;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Defines the API for querying {@link Season}s
 */
@RestController
public class SeasonRestController {

    private SeasonService seasonService;

    public SeasonRestController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping(
            path = "/season",
            produces = "application/json"
    )
    public ResponseEntity seasons(@RequestParam(name = "competition", required = false) String competition,
                                  @RequestParam(name = "name", required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return seasonByName(name);
        } else if (competition != null && !competition.isEmpty()) {
            return seasonsByCompetition(competition);
        }

        return ResponseEntity.ok(seasonService.getSeasons());
    }

    @GetMapping(
            path = "/season/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity seasonById(@PathVariable("id") long id) {
        Season season = seasonService.getSeasonById(id);
        if (season == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(seasonService.getSeasonById(id));
    }

    private ResponseEntity seasonsByCompetition(String competition) {
        List<Season> seasons = seasonService.getSeasonsByCompetition(competition);
        if (seasons == null || seasons.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(seasons);
    }

    private ResponseEntity seasonByName(String name) {
        Season season = seasonService.getSeasonByName(name);
        if (season == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(seasonService.getSeasonByName(name));
    }
}
