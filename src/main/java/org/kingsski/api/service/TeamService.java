package org.kingsski.api.service;

import org.kingsski.api.controller.TeamController;
import org.kingsski.api.model.DisplayableTeam;

import java.util.List;

/**
 * This interface defines the API for a service which provided
 * {@link DisplayableTeam}s to the {@link TeamController}
 */
public interface TeamService {
	
	/**
	 * Get Teams for all leagues and divisions for the latest division in
	 * the current season
	 * 
	 * @return a {@link List} of {@link DisplayableTeam}s
	 */
	List<DisplayableTeam> getTeamsAll();
	
	/**
	 * Get Teams for all divisions for a league for the latest division in
	 * the current season
	 * 
	 * @param league the league to get Teams for
	 * @return a {@link List} of {@link DisplayableTeam}s
	 */
	List<DisplayableTeam> getTeamsByLeague(String league);
	
	/**
	 * Get Teams for all divisions for a specific league for a given division
	 * for the current season
	 * 
	 * @param league The league to get Teams for
	 * @param division The division the get Teams for
	 * @return a {@link List} of {@link DisplayableTeam}s
	 */
	List<DisplayableTeam> getTeamsByLeagueAndDivision(String league, String division);

	
	/**
	 * Get Teams for all divisions for a specific league for a given division
	 * from a specific season.
	 * 
	 * @param season The season to get Teams for. A season is specified by
	 * the year it is started in. e.g. for the 2016/2017 season this would
	 * take the value "2016"
	 * @param league The league to get Teams for
	 * @param division The division to get Teams for
	 *
	 * @return a {@link List} of {@link DisplayableTeam}s
	 */
	List<DisplayableTeam> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division);

}
