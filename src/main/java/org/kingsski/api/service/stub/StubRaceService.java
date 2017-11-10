package org.kingsski.api.service.stub;

import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StubRaceService implements RaceService {
    private static final List<DisplayableRace> RACES = Collections.unmodifiableList(new ArrayList<DisplayableRace>());

    @Override
    public List<DisplayableRace> getRacesAll() {
        return RACES;
    }

    @Override
    public List<DisplayableRace> getRacesByLeague(String league) {
        return RACES;
    }

    @Override
    public List<DisplayableRace> getRacesByLeagueAndRound(String league, String round) {
        return RACES;
    }

    @Override
    public List<DisplayableRace> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return RACES;
    }
}
