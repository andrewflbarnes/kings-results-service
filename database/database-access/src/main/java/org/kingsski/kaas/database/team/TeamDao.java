package org.kingsski.kaas.database.team;

import java.util.List;

/**
 * Defines the DAO operations for the TEAM view (querying)
 */
public interface TeamDao {

    /**
     * Get a list of all teams
     * @return a list of teams
     */
    List<Team> getTeams();

    /**
     * Get a team by id
     * @param id the id of the team
     * @return a team if it exists, null otherwise
     */
    Team getTeamById(long id);

    /**
     * Get a team by name
     * @param name the name of the team
     * @return a team if it exists, null otherwise
     */
    Team getTeamByName(String name);

    /**
     * Add a new team
     * @param name The name of the team
     * @param club The name of the club to add the team to
     * @return a team representing the newly added team
     */
    Team addTeam(String name, String club);
}
