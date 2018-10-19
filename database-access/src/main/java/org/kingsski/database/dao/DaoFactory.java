package org.kingsski.database.dao;

/**
 * Defines a factory for providing DAOs
 */
public interface DaoFactory {

    /**
     * Get a new instance of a {@link ClubDao}
     *
     * @return a DAO
     */
    ClubDao newClubDao();
}
