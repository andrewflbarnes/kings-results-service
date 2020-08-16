package org.kingsski.kaas.service.team;

import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.kingsski.kaas.service.exception.EntityMissingParentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This service defines and implements operations for {@link Team}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Team}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class TeamService {

    private final TeamDao teamDao;

    private final ClubDao clubDao;

    public TeamService(TeamDao teamDao, ClubDao clubDao) {
        this.teamDao = teamDao;
        this.clubDao = clubDao;
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
     * @param clubName The name of the club to add the texam to
     * @return a {@link Team} if added successfully, null if the club did not exist
     * @throws EntityMissingParentException if the club does not exist
     * @throws EntityAlreadyExistsException if the team already exists
     */
    @Transactional
    public Team addTeam(String name, String clubName) throws EntityMissingParentException, EntityAlreadyExistsException {
        final Club club = clubDao.getClubByName(clubName);

        if (club == null) {
            throw new EntityMissingParentException("team", name, "club", "name", clubName);
        }

        final Team team = teamDao.getTeamByName(name);

        if (team != null) {
            throw new EntityAlreadyExistsException("team", "name", name);
        }

        return teamDao.addTeam(name, clubName);
    }
}
