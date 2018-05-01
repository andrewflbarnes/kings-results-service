package org.kingsski.api.service;

import org.kingsski.api.dao.team.TeamDao;
import org.kingsski.api.model.Team;

import java.util.List;

/**
 * This class defines the service providing {@link Team}s
 */
public class TeamService {

    private TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    /**
     * Get Teams for all leagues and divisions for the latest division in
     * the current season
     *
     * @return a {@link List} of {@link Team}s
     */
    public List<Team> getTeamsAll() {
        return teamDao.getTeamsAll();
    }

    /**
     * Get Teams for all divisions for a league for the latest division in
     * the current season
     *
     * @param league the league to get Teams for
     * @return a {@link List} of {@link Team}s
     */
    public List<Team> getTeamsByLeague(String league) {
        return teamDao.getTeamsByLeague(league);
    }

    /**
     * Get Teams for all divisions for a specific league for a given division
     * for the current season
     *
     * @param league The league to get Teams for
     * @param division The division the get Teams for
     * @return a {@link List} of {@link Team}s
     */
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return teamDao.getTeamsByLeagueAndDivision(league, division);
    }

    /**
     * Get Teams for all divisions for a specific league for a given division
     * from a specific season.
     *
     * @param season The season to get Teams for. A season is specified by
     * the year it is started in. e.g. for the 2016/2017 season this would
     * take the value "2016"
     * @param league The league to get Teams for
     * @param division The division to get Teams for
     *
     * @return a {@link List} of {@link Team}s
     */
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return teamDao.getTeamsBySeasonAndLeagueAndDivision(season, league, division);
    }
}
