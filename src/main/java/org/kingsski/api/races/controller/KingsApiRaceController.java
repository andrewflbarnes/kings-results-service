package org.kingsski.api.races.controller;

import java.util.List;

import org.kingsski.api.races.controller.service.KingsApiRaceService;
import org.kingsski.data.model.RaceDisplayable;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class KingsApiRaceController {
	
	private KingsApiRaceService kingsApiRaceService;
	
	@RequestMapping("")
	public List<RaceDisplayable> races() {
		return kingsApiRaceService.getRacesAll();
	}
	
	@RequestMapping("{league}")
	public List<RaceDisplayable> racesByLeague(@PathVariable("league") String league) {
		
		return kingsApiRaceService.getRacesByLeague(league);
	}
	
	@RequestMapping("{league}/{round}")
	public List<RaceDisplayable> racesByLeagueByRound(
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return kingsApiRaceService.getRacesByLeagueAndRound(league, round);
	}
	
	@RequestMapping("{season}/{league}/{round}")
	public List<RaceDisplayable> racesByLeagueBySeasonByRound(
			@PathVariable("season") String season,
			@PathVariable("league") String league,
			@PathVariable("round") String round) {
		
		return kingsApiRaceService.getRacesBySeasonAndLeagueAndRound(season, league, round);
	}
	
	@Required
	public void setKingsApiRaceService(KingsApiRaceService kingsApiRaceService) {
		this.kingsApiRaceService = kingsApiRaceService;
	}

}
