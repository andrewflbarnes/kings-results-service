package org.kingsski.kaas.service.scoreregional;

import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link ScoreRegional}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link ScoreRegional}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class ScoreRegionalService {

    private ScoreRegionalDao scoreRegionalDao;

    public ScoreRegionalService(ScoreRegionalDao scoreRegionalDao) {
        this.scoreRegionalDao = scoreRegionalDao;
    }

    /**
     * Get all {@link ScoreRegional}s
     *
     * @return a {@link List} of {@link ScoreRegional}s
     */
    public List<ScoreRegional> getScoreRegionals() {
        return scoreRegionalDao.getScoreRegionals();
    }
}
