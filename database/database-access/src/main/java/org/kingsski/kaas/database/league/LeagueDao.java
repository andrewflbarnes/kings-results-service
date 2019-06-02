package org.kingsski.kaas.database.league;

import java.util.List;

/**
 * Defines the DAO operations for the LEAGUE view (querying)
 */
public interface LeagueDao {

    /**
     * Get a list of all leagues
     * @return a list of leagues
     */
    List<League> getLeagues();

    /**
     * Get a list of all leagues by competition
     * @param competition the competition to get leagues for
     * @return a list of leagues
     */
    List<League> getLeaguesByCompetition(String competition);

    /**
     * Get a league by id
     * @param id the id of the league
     * @return a league
     */
    League getLeagueById(long id);

    /**
     * Get a league by name
     * @param name the name of the league
     * @return a league
     */
    League getLeagueByName(String name);
}
