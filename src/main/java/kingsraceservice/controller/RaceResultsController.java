package kingsraceservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kingsraceservice.api.service.RaceService;
import kingsraceservice.dummy.service.DummyRaceService;
import kingsraceservice.model.Race;

/**
 * This class acts as a REST controller for the Kings Race Service providing
 * races as requested. The base URL for requests is "/races". All parameters
 * for this API are path based.
 * 
 * This class uses an implementation of {@link RaceService} to provide the
 * required {@list Race}s
 */
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
	public List<Race> racesByLeague(@PathVariable("league") String league) {
		
		return this.raceService.getRacesByLeague(league);
	}
	
	@RequestMapping("{league}/{round}")
	public List<Race> racesByLeagueByRound(
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesByLeagueAndRound(league, round);
	}
	
	@RequestMapping("{season}/{league}/{round}")
	public List<Race> racesByLeagueBySeasonByRound(
			@PathVariable("season") String season,
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesBySeasonAndLeagueAndRound(season, league, round);
	}
	
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

}
