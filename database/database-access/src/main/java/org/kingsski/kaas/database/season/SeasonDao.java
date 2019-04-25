package org.kingsski.kaas.database.season;

import java.util.List;

/**
 * Defines the DAO operations for the SEASON view (querying)
 */
public interface SeasonDao {

    /**
     * Get a list of all seasons
     * @return a list of seasons
     */
    List<Season> getSeasons();

    /**
     * Get a list of all seasons by competition
     * @param competition the competitioni to get seasons for
     * @return a list of seasons
     */
    List<Season> getSeasonsByCompetition(String competition);

    /**
     * Get a season by id
     * @param id the id of the season
     * @return a season
     */
    Season getSeasonById(long id);

    /**
     * Get a season by name
     * @param name the name of the season
     * @return a season
     */
    Season getSeasonByName(String name);
}
