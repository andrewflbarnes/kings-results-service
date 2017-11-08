package org.kingsski.api.service.caching;

import org.kingsski.api.service.TeamService;
import org.kingsski.wax.data.Team;

import java.util.List;

/**
 * Decorator implementation of {@link TeamService} which caches results provided by another {@link TeamService}.
 * Extends the {@link AbstractCachingService} class to provide the caching functionality.
 */
public class CachingTeamService extends AbstractCachingService<Team> implements TeamService {

    private static final String ALL = "ALL";
    private static final String LEAGUE = "LEAGUE";
    private static final String LEAGUE_DIVISION = "LEAGUE_DIVISION";
    private static final String SEASON_LEAGUE_DIVISION = "SEASON_LEAGUE_DIVISION";

    private TeamService teamService;

    /**
     * Default constructor
     */
    public CachingTeamService(TeamService teamService) {
        super();
        this.teamService = teamService;
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
                return teamService.getTeamsAll();
            case LEAGUE:
                return teamService.getTeamsByLeague(args[0]);
            case LEAGUE_DIVISION:
                return teamService.getTeamsByLeagueAndDivision(args[0], args[1]);
            case SEASON_LEAGUE_DIVISION:
                return teamService.getTeamsBySeasonAndLeagueAndDivision(args[0], args[1], args[2]);
            default:
                throw new IllegalArgumentException("Unrecognised CachingTeamService type: \"" + type + "\"");
        }
    }
}