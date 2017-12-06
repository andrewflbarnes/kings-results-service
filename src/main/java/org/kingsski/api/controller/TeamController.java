package org.kingsski.api.controller;

import org.kingsski.api.service.TeamService;
import org.kingsski.wax.data.Team;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * This class acts as a REST controller for the Kings Team Service providing
 * Teams as requested. The base URL for requests is "/Teams". All parameters
 * for this API are path based.
 * 
 * This class uses an implementation of {@link TeamService} to provide the
 * required {@link Team}s
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
public class TeamController {

	@Resource(name = "repositoryTeamService")
	private TeamService teamService;
	
	@RequestMapping("")
	public List<Team> teams() {
		return teamService.getTeamsAll();
	}
	
	@RequestMapping("{league}")
	public List<Team> teamsByLeague(@PathVariable("league") String league) {
		
		return this.teamService.getTeamsByLeague(league);
	}
	
	@RequestMapping("{league}/{division}")
	public List<Team> teamsByLeagueByDivision(
			@PathVariable("league") String league,
			@PathVariable("division") String division) {
		
		return this.teamService.getTeamsByLeagueAndDivision(league, division);
	}
	
	@RequestMapping("{season:[0-9]{4}}/{league}/{division}")
	public List<Team> teamsBySeasonByLeagueByDivision(
			@PathVariable("season") String season,
			@PathVariable("league") String league,
			@PathVariable("division") String division) {
		
		return this.teamService.getTeamsBySeasonAndLeagueAndDivision(season, league, division);
	}

	// TODO Remove (only used in test)
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
}
