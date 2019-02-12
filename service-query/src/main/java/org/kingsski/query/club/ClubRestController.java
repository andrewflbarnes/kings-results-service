package org.kingsski.query.club;

import org.kingsski.database.club.Club;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Defines the API for querying {@link Club}s
 */
@RestController
public class ClubRestController {

    @Resource
    private ClubService clubService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/clubs",
            produces = "application/json"
    )
    public ResponseEntity clubs() {
        return ResponseEntity.ok(clubService.getClubs());
    }

    @RequestMapping(
            method = RequestMethod.GET,
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

    @RequestMapping(
            method = RequestMethod.GET,
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
