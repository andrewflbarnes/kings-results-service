package org.kingsski.database.dao;

import org.kingsski.database.model.Club;

import java.util.List;

public interface ClubDao {

    /**
     * Create a new club
     * @return the club created including the id
     */
    public Club createClub(Club club);

    /**
     * Get a list of all clubs
     * @return a list of clubs
     */
    public List<Club> getClubs();

    /**
     * Get a club by id
     * @param id the id of the club
     * @return a club
     */
    public Club getClubById(long id);

    /**
     * Get a club by name
     * @param name the name of the club
     * @return a club
     */
    public Club getClubByName(String name);

    /**
     * Update a club
     * @param club the update details
     */
    public void updateClub(Club club);
}
