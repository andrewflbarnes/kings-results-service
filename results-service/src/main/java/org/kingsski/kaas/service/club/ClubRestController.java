package org.kingsski.kaas.service.club;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.service.exception.EntityConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Defines the API for querying {@link Club}s
 */
@RestController
public class ClubRestController {

    private final ClubService clubService;

    public ClubRestController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping(
            path = "/club",
            produces = "application/json"
    )
    public ResponseEntity clubs() {
        return ResponseEntity.ok(clubService.getClubs());
    }

    @GetMapping(
            path = "/club/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity clubById(@PathVariable("id") long id) {
        Club club = clubService.getClubById(id);
        if (club == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(club);
    }

    @GetMapping(
            path = "/club/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity clubByName(@PathVariable("name") String name) {
        Club club = clubService.getClubByName(name);
        if (club == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(club);
    }

    @PostMapping(
            path = "/club",
            produces = "application/json"
    )
    public ResponseEntity addClub(@RequestBody Map<String, String> body) throws EntityConflictException {
        final String name = body.get("name");

        final Club club = clubService.addClub(name);

        return ResponseEntity.status(HttpStatus.CREATED).body(club);
    }
}
