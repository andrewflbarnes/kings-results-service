package org.kingsski.kaas.service.season;

import org.kingsski.kaas.database.season.Season;
import org.kingsski.kaas.database.season.SeasonDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link Season}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Season}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class SeasonService {

    private SeasonDao seasonDao;

    public SeasonService(SeasonDao seasonDao) {
        this.seasonDao = seasonDao;
    }

    /**
     * Get all {@link Season}s
     *
     * @return a {@link List} of {@link Season}s
     */
    public List<Season> getSeasons() {
        return seasonDao.getSeasons();
    }

    /**
     * Get all {@link Season}s by competition
     *
     * @return a {@link List} of {@link Season}s
     */
    public List<Season> getSeasonsByCompetition(String competition) {
        return seasonDao.getSeasonsByCompetition(competition);
    }

    /**
     * Get a {@link Season} by name
     *
     * @param name the name of the season to get
     * @return a {@link Season}
     */
    public Season getSeasonByName(String name) {
        return seasonDao.getSeasonByName(name);
    }

    /**
     * Get a {@link Season} by id
     *
     * @param id the id of the season to get
     * @return a {@link Season}
     */
    public Season getSeasonById(long id) {
        return seasonDao.getSeasonById(id);
    }
}
