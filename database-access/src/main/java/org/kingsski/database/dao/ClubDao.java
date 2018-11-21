package org.kingsski.database.dao;

import org.kingsski.database.model.Club;

import java.util.List;

/**
 * Defines the DAO operations for the CLUB view (querying)
 */
public interface ClubDao {

    /**
     * Get a list of all clubs
     * @return a list of clubs
     */
    List<Club> getClubs();

    /**
     * Get a club by id
     * @param id the id of the club
     * @return a club
     */
    Club getClubById(long id);

    /**
     * Get a club by name
     * @param name the name of the club
     * @return a club
     */
    Club getClubByName(String name);
}
