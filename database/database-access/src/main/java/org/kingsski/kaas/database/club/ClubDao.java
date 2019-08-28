package org.kingsski.kaas.database.club;

import org.kingsski.kaas.database.exception.EntityAlreadyExistsException;

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
     * @return a club if it exists, null otherwise
     */
    Club getClubById(long id);

    /**
     * Get a club by name
     * @param name the name of the club
     * @return a club if it exists, null otherwise
     */
    Club getClubByName(String name);

    /**
     * Add a new club
     * @param name The name of the club
     * @return a club representing the newly added club
     * @throws EntityAlreadyExistsException if a club with the same name already exists
     */
    Club addClub(String name);
}
