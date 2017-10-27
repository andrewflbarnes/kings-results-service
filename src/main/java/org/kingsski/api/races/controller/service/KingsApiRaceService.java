package org.kingsski.api.races.controller.service;

import java.util.List;

import org.kingsski.api.races.controller.KingsApiRaceController;
import org.kingsski.data.model.RaceDisplayable;

/**
 * This interface defines the API for a service which provided
 * {@link RaceDisplayable}s to the {@link KingsApiRaceController}
 */
public interface KingsApiRaceService {
	
	/**
	 * Get races for all leagues and divisions for the latest round in
	 * the current season
	 * 
	 * @return a {@link List} of {@link RaceDisplayable}s
	 */
	List<RaceDisplayable> getRacesAll();
	
	/**
	 * Get races for all divisions for a league for the latest round in
	 * the current season
	 * 
	 * @param league the league to get races for
	 * @return a {@link List} of {@link RaceDisplayable}s
	 */
	List<RaceDisplayable> getRacesByLeague(String league);
	
	/**
	 * Get races for all divisions for a specific league for a given round
	 * for the current season
	 * 
	 * @param league The league to get races for
	 * @param round The round the get races for
	 * @return a {@link List} of {@link RaceDisplayable}s
	 */
	List<RaceDisplayable> getRacesByLeagueAndRound(String league, String round);

	
	/**
	 * Get races for all divisions for a specific league for a given round
	 * from a specific season.
	 * 
	 * @param season The season to get races for
	 * @param league The league to get races for
	 * @param round The season to get races for. A season is specified by
	 * the year it is started in. e.g. for the 2016/2017 season this would
	 * take the value "2016"
	 * @return a {@link List} of {@link RaceDisplayable}s
	 */
	List<RaceDisplayable> getRacesBySeasonAndLeagueAndRound(String season, String league, String round);

}
