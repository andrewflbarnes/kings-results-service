package org.kingsski.kaas.service.league;

import org.kingsski.kaas.database.league.League;
import org.kingsski.kaas.database.league.LeagueDao;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link League}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link League}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class LeagueService {

    private final LeagueDao leagueDao;

    public LeagueService(LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }

    /**
     * Get all {@link League}s
     *
     * @return a {@link List} of {@link League}s
     */
    public List<League> getLeagues() {
        return leagueDao.getLeagues();
    }

    /**
     * Get all {@link League}s by competition
     *
     * @return a {@link List} of {@link League}s
     */
    public List<League> getLeaguesByCompetition(String competition) {
        return leagueDao.getLeaguesByCompetition(competition);
    }

    /**
     * Get a {@link League} by name
     *
     * @param name the name of the league to get
     * @return a {@link League}
     */
    public League getLeagueByName(String name) {
        return leagueDao.getLeagueByName(name);
    }

    /**
     * Get a {@link League} by id
     *
     * @param id the id of the league to get
     * @return a {@link League}
     */
    public League getLeagueById(long id) {
        return leagueDao.getLeagueById(id);
    }
}
