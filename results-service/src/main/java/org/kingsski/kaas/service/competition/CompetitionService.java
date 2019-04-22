package org.kingsski.kaas.service.competition;

import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * This service defines and implements operations for {@link Competition}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Competition}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class CompetitionService {

    @Resource
    private CompetitionDao competitionDao;

    public CompetitionService() {
        // Default constructor
    }

    /**
     * Get all {@link Competition}s
     *
     * @return a {@link List} of {@link Competition}s
     */
    public List<Competition> getCompetitions() {
        return competitionDao.getCompetitions();
    }

    /**
     * Get a {@link Competition} by name
     *
     * @param name the name of the competition to get
     * @return a {@link Competition}
     */
    public Competition getCompetitionByName(String name) {
        return competitionDao.getCompetitionByName(name);
    }

    /**
     * Get a {@link Competition} by id
     *
     * @param id the id of the competition to get
     * @return a {@link Competition}
     */
    public Competition getCompetitionById(long id) {
        return competitionDao.getCompetitionById(id);
    }
}
