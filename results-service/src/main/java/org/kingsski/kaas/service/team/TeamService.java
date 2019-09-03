package org.kingsski.kaas.service.team;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private ClubDao clubDao;

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

    /**
     * Add a new team with a given name
     *
     * @param name The name of the new team
     * @param clubName The name of the club to add the team to
     * @return a {@link Team} if added successfully, null if the club did not exist
     */
    @Transactional
    public Team addTeam(String name, String clubName) {
        final Club club = clubDao.getClubByName(clubName);

        if (club == null) {
            return null;
        }

        return teamDao.addTeam(name, clubName);
    }
}
