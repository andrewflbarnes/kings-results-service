package org.kingsski.kaas.database.regional;

import java.util.List;

/**
 * Defines the DAO operations for the REGIONAL view (querying)
 */
public interface RegionalDao {

    /**
     * Get a list of all regionals
     * @return a list of regionals
     */
    List<Regional> getRegionals();

    /**
     * Get a list of all regionals by competition
     * @param competition the competition to get regionals for
     * @return a list of regionals
     */
    List<Regional> getRegionalsByCompetition(String competition);

    /**
     * Get a regional by id
     * @param id the id of the regional
     * @return a regional
     */
    Regional getRegionalById(long id);
}
