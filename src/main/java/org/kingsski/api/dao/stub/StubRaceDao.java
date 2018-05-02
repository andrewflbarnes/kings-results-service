package org.kingsski.api.dao.stub;

import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.model.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StubRaceDao implements RaceDao {
    private static final List<Race> RACES = Collections.unmodifiableList(new ArrayList<Race>());

    @Override
    public List<Race> getRacesAll() {
        return RACES;
    }

    @Override
    public List<Race> getRacesByLeague(String league) {
        return RACES;
    }

    @Override
    public List<Race> getRacesByLeagueAndRound(String league, String round) {
        return RACES;
    }

    @Override
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return RACES;
    }
}
