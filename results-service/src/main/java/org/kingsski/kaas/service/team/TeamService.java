package org.kingsski.kaas.service.team;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * This service defines and implements operations for {@link Team}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Team}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class TeamService {

    @Resource
    private TeamDao teamDao;

    public TeamService() {
        // Default constructor
    }

    /**
     * Get all {@link Team}s
     *
     * @return a {@link List} of {@link Team}s
     */
    public List<Team> getTeams() {
        return teamDao.getTeams();
    }

    /**
     * Get a {@link Team} by name
     *
     * @param name the name of the team to get
     * @return a {@link Team}
     */
    public Team getTeamByName(String name) {
        return teamDao.getTeamByName(name);
    }

    /**
     * Get a {@link Team} by id
     *
     * @param id the id of the team to get
     * @return a {@link Team}
     */
    public Team getTeamById(long id) {
        return teamDao.getTeamById(id);
    }
}
