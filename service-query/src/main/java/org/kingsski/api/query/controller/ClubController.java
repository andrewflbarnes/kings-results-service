package org.kingsski.api.query.controller;

import org.kingsski.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClubController {

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
}
