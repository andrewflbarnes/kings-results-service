package org.kingsski.kaas.service.club;

import org.kingsski.kaas.database.club.Club;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Defines the API for querying {@link Club}s
 */
@RestController
public class ClubRestController {

    @Resource
    private ClubService clubService;

    @GetMapping(
            path = "/clubs",
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
        return ResponseEntity.ok(clubService.getClubById(id));
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
        return ResponseEntity.ok(clubService.getClubByName(name));
    }
}
