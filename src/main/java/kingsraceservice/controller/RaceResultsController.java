package kingsraceservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kingsraceservice.api.service.RaceService;
import kingsraceservice.dummy.service.DummyRaceService;
import kingsraceservice.model.Race;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/races")
public class RaceResultsController {
	
	private RaceService raceService;
	
	{ this.raceService = new DummyRaceService(); }
	
	@RequestMapping("")
	public List<Race> races() {
		return raceService.getRacesAll();
	}
	
	@RequestMapping("{league}")
	public List<Race> races(@PathVariable("league") String league) {
		
		if ("ALL".equals(league)) {
			return this.raceService.getRacesAll();
		} else {
			return this.raceService.getRacesByLeague(league);
		}
	}
	
	@RequestMapping("{league}/{round}")
	public List<Race> races(
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesByLeagueAndRound(league, round);
	}
	
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

}
