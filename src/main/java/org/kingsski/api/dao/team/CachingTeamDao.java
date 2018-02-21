package org.kingsski.api.dao.team;

import org.kingsski.api.dao.caching.AbstractCachingDao;
import org.kingsski.api.model.Team;

import java.util.List;

/**
 * Decorator implementation of {@link TeamDao} which caches results provided by another {@link TeamDao}.
 * Extends the {@link AbstractCachingDao} class to provide the caching functionality.
 */
public class CachingTeamDao extends AbstractCachingDao<Team> implements TeamDao {

    private static final String ALL = "ALL";
    private static final String LEAGUE = "LEAGUE";
    private static final String LEAGUE_DIVISION = "LEAGUE_DIVISION";
    private static final String SEASON_LEAGUE_DIVISION = "SEASON_LEAGUE_DIVISION";

    private TeamDao teamDao;

    /**
     * Default constructor
     */
    public CachingTeamDao(TeamDao teamDao) {
        super();
        this.teamDao = teamDao;
    }

    @Override
    public List<Team> getTeamsAll() {
        return getFromCache(ALL);
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        return getFromCache(LEAGUE, league);
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return getFromCache(LEAGUE_DIVISION, league, division);
    }

    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return getFromCache(SEASON_LEAGUE_DIVISION, season, league, division);
    }

    @Override
    protected List<Team> itemsFromService(String type, String... args) {
        switch (type) {
            case ALL:
                return teamDao.getTeamsAll();
            case LEAGUE:
                return teamDao.getTeamsByLeague(args[0]);
            case LEAGUE_DIVISION:
                return teamDao.getTeamsByLeagueAndDivision(args[0], args[1]);
            case SEASON_LEAGUE_DIVISION:
                return teamDao.getTeamsBySeasonAndLeagueAndDivision(args[0], args[1], args[2]);
            default:
                throw new IllegalArgumentException("Unrecognised CachingTeamDao type: \"" + type + "\"");
        }
    }
}
