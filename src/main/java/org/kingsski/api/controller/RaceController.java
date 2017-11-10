package org.kingsski.api.controller;

import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class acts as a REST controller for the Kings DisplayableRace Service providing
 * races as requested. The base URL for requests is "/races". All parameters
 * for this API are path based.
 * 
 * This class uses an implementation of {@link RaceService} to provide the
 * required {@list DisplayableRace}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/races")
public class RaceController {
	
	private RaceService raceService;
	
	@RequestMapping("")
	public List<DisplayableRace> races() {
		return raceService.getRacesAll();
	}
	
	@RequestMapping("{league}")
	public List<DisplayableRace> racesByLeague(@PathVariable("league") String league) {
		
		return this.raceService.getRacesByLeague(league);
	}
	
	@RequestMapping("{league}/{round}")
	public List<DisplayableRace> racesByLeagueByRound(
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesByLeagueAndRound(league, round);
	}
	
	@RequestMapping("{season:[0-9]{4}}/{league}/{round}")
	public List<DisplayableRace> racesBySeasonByLeagueByRound(
			@PathVariable("season") String season,
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesBySeasonAndLeagueAndRound(season, league, round);
	}
	
	@Required
	public void setRaceService(RaceService raceService) {
		this.raceService = raceService;
	}

}
