package org.kingsski.kaas.service.organisation;

import org.kingsski.kaas.database.organisation.Organisation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Defines the API for querying {@link Organisation}s
 */
@RestController
public class OrganisationRestController {

    @Resource
    private OrganisationService organisationService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/organisations",
            produces = "application/json"
    )
    public ResponseEntity organisations() {
        return ResponseEntity.ok(organisationService.getOrganisations());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/organisation/{id:\\d+}",
            produces = "application/json"
    )
    public ResponseEntity organisationById(@PathVariable("id") long id) {
        Organisation organisation = organisationService.getOrganisationById(id);
        if (organisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(organisationService.getOrganisationById(id));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/organisation/{name:[A-Za-z]+.*}",
            produces = "application/json"
    )
    public ResponseEntity organisationByName(@PathVariable("name") String name) {
        Organisation organisation = organisationService.getOrganisationByName(name);
        if (organisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(organisationService.getOrganisationByName(name));
    }
}
