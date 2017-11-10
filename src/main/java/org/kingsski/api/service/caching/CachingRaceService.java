package org.kingsski.api.service.caching;

import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;

import java.util.List;

/**
 * Decorator implementation of {@link RaceService} which caches results provided by another {@link RaceService}.
 * Extends the {@link AbstractCachingService} class to provide the caching functionality.
 */
public class CachingRaceService extends AbstractCachingService<DisplayableRace> implements RaceService {

    private static final String ALL = "ALL";
    private static final String LEAGUE = "LEAGUE";
    private static final String LEAGUE_ROUND = "LEAGUE_ROUND";
    private static final String SEASON_LEAGUE_ROUND = "SEASON_LEAGUE_ROUND";

    private RaceService raceService;

    /**
     * Default constructor
     */
    public CachingRaceService(RaceService raceService) {
        super();
        this.raceService = raceService;
    }

    @Override
    public List<DisplayableRace> getRacesAll() {
        return getFromCache(ALL);
    }

    @Override
    public List<DisplayableRace> getRacesByLeague(String league) {
        return getFromCache(LEAGUE, league);
    }

    @Override
    public List<DisplayableRace> getRacesByLeagueAndRound(String league, String round) {
        return getFromCache(LEAGUE_ROUND, league, round);
    }

    @Override
    public List<DisplayableRace> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return getFromCache(SEASON_LEAGUE_ROUND, season, league, round);
    }

    @Override
    protected List<DisplayableRace> itemsFromService(String type, String... args) {
        switch (type) {
            case ALL:
                return raceService.getRacesAll();
            case LEAGUE:
                return raceService.getRacesByLeague(args[0]);
            case LEAGUE_ROUND:
                return raceService.getRacesByLeagueAndRound(args[0], args[1]);
            case SEASON_LEAGUE_ROUND:
                return raceService.getRacesBySeasonAndLeagueAndRound(args[0], args[1], args[2]);
            default:
                throw new IllegalArgumentException("Unrecognised CachingRaceService type: \"" + type + "\"");
        }
    }
}
