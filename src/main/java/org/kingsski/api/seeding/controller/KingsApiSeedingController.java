package org.kingsski.api.seeding.controller;

import org.kingsski.api.races.controller.service.KingsApiRaceService;
import org.kingsski.data.model.RaceDisplayable;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class acts as a REST controller for the Kings RaceDisplayable Service providing
 * races as requested. The base URL for requests is "/races". All parameters
 * for this API are path based.
 * 
 * This class uses an implementation of {@link KingsApiRaceService} to provide the
 * required {@list RaceDisplayable}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/races")
public class KingsApiSeedingController {
	
	private KingsApiRaceService raceService;
	
	@RequestMapping("")
	public List<RaceDisplayable> races() {
		return raceService.getRacesAll();
	}
	
	@RequestMapping("{league}")
	public List<RaceDisplayable> racesByLeague(@PathVariable("league") String league) {
		
		return this.raceService.getRacesByLeague(league);
	}
	
	@RequestMapping("{league}/{round}")
	public List<RaceDisplayable> racesByLeagueByRound(
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesByLeagueAndRound(league, round);
	}
	
	@RequestMapping("{season}/{league}/{round}")
	public List<RaceDisplayable> racesByLeagueBySeasonByRound(
			@PathVariable("season") String season,
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return this.raceService.getRacesBySeasonAndLeagueAndRound(season, league, round);
	}
	
	@Required
	public void setRaceService(KingsApiRaceService raceService) {
		this.raceService = raceService;
	}

}
