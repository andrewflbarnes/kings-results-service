package org.kingsski.kaas.database.division;

import java.util.List;

/**
 * Defines the DAO operations for the DIVISION view (querying)
 */
public interface DivisionDao {

    /**
     * Get a list of all divisions
     * @return a list of divisions
     */
    List<Division> getDivisions();

    /**
     * Get a list of all divisions by competition
     * @param competition the competition to get divisions for
     * @return a list of divisions
     */
    List<Division> getDivisionsByCompetition(String competition);

    /**
     * Get a division by id
     * @param id the id of the division
     * @return a division
     */
    Division getDivisionById(long id);

    /**
     * Get a division by name
     * @param name the name of the division
     * @return a division
     */
    Division getDivisionByName(String name);
}
