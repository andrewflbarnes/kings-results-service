package org.kingsski.kaas.service.organisation;

import org.kingsski.kaas.database.organisation.Organisation;
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
 * Defines the API for querying {@link Organisation}s
 */
@RestController
public class OrganisationRestController {

    @Resource
    private OrganisationService organisationService;

    @GetMapping(
            path = "/organisation",
            produces = "application/json"
    )
    public ResponseEntity organisations() {
        return ResponseEntity.ok(organisationService.getOrganisations());
    }

    @GetMapping(
            path = "/organisation/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity organisationById(@PathVariable("id") long id) {
        Organisation organisation = organisationService.getOrganisationById(id);
        if (organisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(organisation);
    }

    @GetMapping(
            path = "/organisation/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity organisationByName(@PathVariable("name") String name) {
        Organisation organisation = organisationService.getOrganisationByName(name);
        if (organisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(organisation);
    }

    @PostMapping(
            path = "/organisation",
            produces = "application/json"
    )
    public ResponseEntity addClub(@RequestBody Map<String, String> body) throws EntityConflictException {
        final String name = body.get("name");

        final Organisation org = organisationService.addOrganisation(name);

        return ResponseEntity.status(HttpStatus.CREATED).body(org);
    }
}
