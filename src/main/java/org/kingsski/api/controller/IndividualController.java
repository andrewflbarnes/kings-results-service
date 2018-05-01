package org.kingsski.api.controller;

import org.kingsski.api.model.Individual;
import org.kingsski.api.service.IndividualService;
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
    public List<Individual> individuals(@RequestParam(value = "club",required = false) String club) {
        if (club == null || club.isEmpty()) {
            return individualService.getIndividualsAll();
        } else {
            return individualService.getIndividualsByClub(club);
        }
    }

    @RequestMapping("{discipline}")
    public List<Individual> individualsByDiscipline(@PathVariable("discipline") String discipline) {
        return individualService.getIndividualsByDiscipline(discipline);
    }
}
