package org.kingsski.kaas.service.division;

import org.kingsski.kaas.database.division.Division;
import org.kingsski.kaas.database.division.DivisionDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link Division}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Division}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class DivisionService {

    private final DivisionDao divisionDao;

    public DivisionService(DivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

    /**
     * Get all {@link Division}s
     *
     * @return a {@link List} of {@link Division}s
     */
    public List<Division> getDivisions() {
        return divisionDao.getDivisions();
    }

    /**
     * Get all {@link Division}s by competition
     *
     * @return a {@link List} of {@link Division}s
     */
    public List<Division> getDivisionsByCompetition(String competition) {
        return divisionDao.getDivisionsByCompetition(competition);
    }

    /**
     * Get a {@link Division} by name
     *
     * @param name the name of the division to get
     * @return a {@link Division}
     */
    public Division getDivisionByName(String name) {
        return divisionDao.getDivisionByName(name);
    }

    /**
     * Get a {@link Division} by id
     *
     * @param id the id of the division to get
     * @return a {@link Division}
     */
    public Division getDivisionById(long id) {
        return divisionDao.getDivisionById(id);
    }
}
