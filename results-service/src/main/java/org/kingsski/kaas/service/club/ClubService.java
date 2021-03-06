package org.kingsski.kaas.service.club;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link Club}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Club}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class ClubService {

    private final ClubDao clubDao;

    public ClubService(ClubDao clubDao) {
        this.clubDao = clubDao;
    }

    /**
     * Get all {@link Club}s
     *
     * @return a {@link List} of {@link Club}s
     */
    public List<Club> getClubs() {
        return clubDao.getClubs();
    }

    /**
     * Get a {@link Club} by name
     *
     * @param name the name of the club to get
     * @return a {@link Club}
     */
    public Club getClubByName(String name) {
        return clubDao.getClubByName(name);
    }

    /**
     * Get a {@link Club} by id
     *
     * @param id the id of the club to get
     * @return a {@link Club}
     */
    public Club getClubById(long id) {
        return clubDao.getClubById(id);
    }

    /**
     * Add a new club with a given name
     *
     * @param name The name of the new club
     * @return a {@link Club}
     * @throws EntityAlreadyExistsException if the club already exists
     */
    public Club addClub(String name) throws EntityAlreadyExistsException {
        Club club = clubDao.getClubByName(name);
        if (club != null) {
            throw new EntityAlreadyExistsException("club", "name", name);
        }
        return clubDao.addClub(name);
    }
}
