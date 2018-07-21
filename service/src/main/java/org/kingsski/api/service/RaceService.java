package org.kingsski.api.service;

import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.model.Race;

import java.util.List;

/**
 * This class defines the service providing {@link Race}s
 */
public class RaceService {

    private RaceDao raceDao;

    public RaceService(RaceDao raceDao) {
        this.raceDao = raceDao;
    }
    
    /**
     * Get races for all leagues and divisions for the latest round in
     * the current season
     * 
     * @return a {@link List} of {@link Race}s
     */
    public List<Race> getRacesAll() {
        return raceDao.getRacesAll();
    }
    
    /**
     * Get races for all divisions for a league for the latest round in
     * the current season
     * 
     * @param league the league to get races for
     * @return a {@link List} of {@link Race}s
     */
    public List<Race> getRacesByLeague(String league) {
        return raceDao.getRacesByLeague(league);
    }
    
    /**
     * Get races for all divisions for a specific league for a given round
     * for the current season
     * 
     * @param league The league to get races for
     * @param round The round the get races for
     * @return a {@link List} of {@link Race}s
     */
    public List<Race> getRacesByLeagueAndRound(String league, String round) {
        return raceDao.getRacesByLeagueAndRound(league, round);
    }

    
    /**
     * Get races for all divisions for a specific league for a given round
     * from a specific season.
     * 
     * @param season The season to get races for
     * @param league The league to get races for
     * @param round The season to get races for. A season is specified by
     * the year it is started in. e.g. for the 2016/2017 season this would
     * take the value "2016"
     * @return a {@link List} of {@link Race}s
     */
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return raceDao.getRacesBySeasonAndLeagueAndRound(season, league, round);
    }
}
