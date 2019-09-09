package org.kingsski.kaas.service.team;

import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.service.exception.EntityConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Defines the API for querying {@link Team}s
 */
@RestController
public class TeamRestController {

    @Resource
    private TeamService teamService;

    @GetMapping(
            path = "/team",
            produces = "application/json"
    )
    public ResponseEntity teams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping(
            path = "/team/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity teamById(@PathVariable("id") long id) {
        Team team = teamService.getTeamById(id);
        if (team == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @GetMapping(
            path = "/team/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity teamByName(@PathVariable("name") String name) {
        Team team = teamService.getTeamByName(name);
        if (team == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @PostMapping(
            path = "/team",
            produces = "application/json"
    )
    public ResponseEntity addTeam(@RequestBody Map<String, String> body) throws EntityConflictException {
        final String club = body.get("club");
        final String name = body.get("name");

        final Team team = teamService.addTeam(name, club);

        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }
}
