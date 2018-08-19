package org.kingsski.api.query.controller;

import org.kingsski.api.query.service.IndividualService;
import org.kingsski.data.model.Individual;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class acts as a REST controller for the Kings Individual Service providing
 * individuals as requested. The base URL for requests is "/individuals". All parameters
 * for this API are path based.
 * 
 * This class uses {@link IndividualService} to provide the required {@link Individual}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/individuals")
public class IndividualController {

    private IndividualService individualService;

    public IndividualController(IndividualService individualService) {
        this.individualService = individualService;
    }

    @RequestMapping("")
    public ResponseEntity individuals(@RequestParam(value = "club", required = false) String club) {
        List<Individual> individuals;
        if (club == null || club.isEmpty()) {
            individuals = individualService.getIndividualsAll();
        } else {
            individuals = individualService.getIndividualsByClub(club);
        }

        return ControllerUtils.asResponseEntityOk(individuals);
    }

    @RequestMapping("{discipline}")
    public ResponseEntity individualsByDiscipline(@PathVariable("discipline") String discipline) {
        return ControllerUtils.asResponseEntityOk(individualService.getIndividualsByDiscipline(discipline));
    }
}
