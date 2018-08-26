package org.kingsski.data.dao.caching;

import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.model.Race;

import java.util.List;

/**
 * Decorator implementation of {@link RaceDao} which caches results provided by another {@link RaceDao}.
 * Extends the {@link AbstractCachingDao} class to provide the caching functionality.
 */
public class CachingRaceDao extends AbstractCachingDao<Race> implements RaceDao {

    private static final String ALL = "ALL";
    private static final String LEAGUE = "LEAGUE";
    private static final String LEAGUE_ROUND = "LEAGUE_ROUND";
    private static final String SEASON_LEAGUE_ROUND = "SEASON_LEAGUE_ROUND";

    private RaceDao raceDao;

    /**
     * Default constructor
     */
    public CachingRaceDao(RaceDao raceDao) {
        super();
        this.raceDao = raceDao;
    }

    @Override
    public List<Race> getRacesAll() {
        return getFromCache(ALL);
    }

    @Override
    public List<Race> getRacesByLeague(String league) {
        return getFromCache(LEAGUE, league);
    }

    @Override
    public List<Race> getRacesByLeagueAndRound(String league, String round) {
        return getFromCache(LEAGUE_ROUND, league, round);
    }

    @Override
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return getFromCache(SEASON_LEAGUE_ROUND, season, league, round);
    }

    @Override
    protected List<Race> itemsFromService(String type, String... args) {
        switch (type) {
            case ALL:
                return raceDao.getRacesAll();
            case LEAGUE:
                return raceDao.getRacesByLeague(args[0]);
            case LEAGUE_ROUND:
                return raceDao.getRacesByLeagueAndRound(args[0], args[1]);
            case SEASON_LEAGUE_ROUND:
                return raceDao.getRacesBySeasonAndLeagueAndRound(args[0], args[1], args[2]);
            default:
                throw new IllegalArgumentException("Unrecognised CachingRaceDao type: \"" + type + "\"");
        }
    }
}
