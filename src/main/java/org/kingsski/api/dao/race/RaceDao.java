package org.kingsski.api.dao.race;

import java.util.List;

import org.kingsski.api.controller.RaceController;
import org.kingsski.api.model.Race;

/**
 * This interface defines the API for a dao which provided
 * {@link Race}s to the {@link RaceController}
 */
public interface RaceDao {

    /**
     * Get races for all leagues and divisions for the latest round in
     * the current season
     *
     * @return a {@link List} of {@link Race}s
     */
    List<Race> getRacesAll();

    /**
     * Get races for all divisions for a league for the latest round in
     * the current season
     *
     * @param league the league to get races for
     * @return a {@link List} of {@link Race}s
     */
    List<Race> getRacesByLeague(String league);

    /**
     * Get races for all divisions for a specific league for a given round
     * for the current season
     *
     * @param league The league to get races for
     * @param round The round the get races for
     * @return a {@link List} of {@link Race}s
     */
    List<Race> getRacesByLeagueAndRound(String league, String round);


    /**
     * Get races for all divisions for a specific league for a given round
     * from a specific season.
     *
     * @param season The season to get races for
     * @param league The league to get races for
     * @param round The season to get races for. A season is specified by
     * the year it is started in. e.g. for the 2016/2017 season this would
     * take the value "2016"
     * @return a {@link List} of {@link Race}s
     */
    List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round);

}
