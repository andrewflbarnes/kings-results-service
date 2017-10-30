package org.kingsski.api.service.caching;

import org.kingsski.api.model.Race;
import org.kingsski.api.service.RaceService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Decorator implementation of {@link RaceService} which caches results provided by another {@link RaceService}.
 */
public class CachingRaceService implements RaceService {

    private RaceService raceService;
    private long interval = 60000;
    private Map<String, RaceCache> cachedRaces = new ConcurrentHashMap<>();

    /**
     * Default constructor
     */
    public CachingRaceService(RaceService raceService) {
        this.raceService = raceService;
    }

    @Override
    public List<Race> getRacesAll() {
        return getFromCache(CacheType.ALL);
    }

    @Override
    public List<Race> getRacesByLeague(String league) {
        return getFromCache(CacheType.LEAGUE, league);
    }

    @Override
    public List<Race> getRacesByLeagueAndRound(String league, String round) {
        return getFromCache(CacheType.LEAGUE_ROUND, league, round);
    }

    @Override
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return getFromCache(CacheType.SEASON_LEAGUE_ROUND, season, league, round);
    }

    /**
     * Set the minimum interval (in ms) which must elapse before a call is made to the {@link RaceService} to update
     * a cached set of {@link Race}s
     *
     * @param interval the interval in ms
     */
    public void setInterval(long interval) {
        this.interval = interval;
    }

    /**
     * Converts a {@link CacheType} and an array of string arguments to a string key
     *
     * @param type the {@link CacheType} for this key
     * @param args the arguments for this key
     * @return a key built from the type and args
     */
    private String toKey(CacheType type, String... args) {
        return type.name() + String.join(",", args);
    }

    /**
     * Retrieves a list of {@link Race}s from the caching. If the races are null, empty or have not been updated for
     * the required interval they are retrieved from the {@link RaceService} associated with this class.
     *
     * @param type the {@link CacheType} that these races are for
     * @param args the arguments required to retrieve these races
     * @return a list of {@link Race}s
     */
    private List<Race> getFromCache(CacheType type, String... args) {
        String key = toKey(type, args);
        RaceCache cache = cachedRaces.get(key);

        if (cache == null) {
            cachedRaces.putIfAbsent(key, new RaceCache());
            cache = cachedRaces.get(key);
        }

        List<Race> races = cache.getRaces();

        if ((cache.getUpdateTime() + interval) < System.currentTimeMillis() || races == null || races.isEmpty()) {
            races = updateCacheFromService(type, args);
        }

        return races;
    }

    /**
     * If applicable retrieve the list of {@link Race}s from the {@link RaceService} and update the caching with them.
     * These races are then returned to the calling method. If an update via the service was not required, the original
     * list of {@link Race}s associated with the caching is returned.
     * <p>
     * The caching is updated if the race list is empty, null or has not been updated for the interval assoicated with
     * this instance.
     *
     * @param type the {@link CacheType} that these races are for
     * @param args the arguments required to retrieve these races
     * @return a list of {@link Race}s
     */
    private List<Race> updateCacheFromService(CacheType type, String... args) {
        final RaceCache cache = cachedRaces.get(toKey(type, args));

        synchronized (cache) {
            List<Race> races = cache.getRaces();
            long now = System.currentTimeMillis();

            if ((cache.getUpdateTime() + interval) < now || races == null || races.isEmpty()) {
                switch (type) {
                    case ALL:
                        races = raceService.getRacesAll();
                        break;
                    case LEAGUE:
                        races = raceService.getRacesByLeague(args[0]);
                        break;
                    case LEAGUE_ROUND:
                        races = raceService.getRacesByLeagueAndRound(args[0], args[1]);
                        break;
                    case SEASON_LEAGUE_ROUND:
                        races = raceService.getRacesBySeasonAndLeagueAndRound(args[0], args[1], args[2]);
                        break;
                    default:
                        throw new IllegalArgumentException("Unrecognised CachingRaceService type: \"" + type.name() + "\"");
                }
                cache.setRaces(races);
                cache.setUpdateTime(now);
            }

            return races;
        }
    }

    /**
     * Defines the available {@link RaceService} call types
     */
    private enum CacheType {
        ALL, LEAGUE, LEAGUE_ROUND, SEASON_LEAGUE_ROUND;
    }

    /**
     * Holder for a list of {@link Race}s associated with a last updated time
     */
    private static final class RaceCache {
        private List<Race> races;
        private long updateTime;

        /**
         * Default constructor
         */
        public RaceCache() {
        }

        /**
         * @return the list of {@link Race}s
         */
        public List<Race> getRaces() {
            return races;
        }

        /**
         * @param races the list of {@link Race}s to set
         */
        public void setRaces(List<Race> races) {
            this.races = races;
        }

        /**
         * @return the last update time
         */
        public long getUpdateTime() {
            return updateTime;
        }

        /**
         * @param updateTime the last update time to set
         */
        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }
    }
}
