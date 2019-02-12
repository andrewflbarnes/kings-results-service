package org.kingsski.database.dao;

import org.kingsski.database.model.Competition;

import java.util.List;

/**
 * Defines the DAO operations for the COMPETITION view (querying)
 */
public interface CompetitionDao {

    /**
     * Get a list of all competitions
     * @return a list of competitions
     */
    List<Competition> getCompetitions();

    /**
     * Get a competition by id
     * @param id the id of the competition
     * @return a competition
     */
    Competition getCompetitionById(long id);

    /**
     * Get a competition by name
     * @param name the name of the competition
     * @return a competition
     */
    Competition getCompetitionByName(String name);
}
