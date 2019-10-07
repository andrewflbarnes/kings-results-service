package org.kingsski.kaas.service.regional;

import org.kingsski.kaas.database.regional.Regional;
import org.kingsski.kaas.database.regional.RegionalDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link Regional}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Regional}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class RegionalService {

    private final RegionalDao regionalDao;

    public RegionalService(RegionalDao regionalDao) {
        this.regionalDao = regionalDao;
    }

    /**
     * Get all {@link Regional}s
     *
     * @return a {@link List} of {@link Regional}s
     */
    public List<Regional> getRegionals() {
        return regionalDao.getRegionals();
    }

    /**
     * Get all {@link Regional}s by competition
     *
     * @return a {@link List} of {@link Regional}s
     */
    public List<Regional> getRegionalsByCompetition(String competition) {
        return regionalDao.getRegionalsByCompetition(competition);
    }

    /**
     * Get a {@link Regional} by id
     *
     * @param id the id of the regional to get
     * @return a {@link Regional}
     */
    public Regional getRegionalById(long id) {
        return regionalDao.getRegionalById(id);
    }
}
