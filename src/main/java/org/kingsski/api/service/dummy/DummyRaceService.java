package org.kingsski.api.service.dummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;

/**
 * A dummy implementation of the {@link RaceService}  interface. This class
 * has a single static list of {@link DisplayableRace}s which it returns for all
 * method calls.
 * <p>
 * On each call it will "cycle" through the races causing the in progress
 * race to move ahead one and all previous races to have a winner set.
 * <p>
 * Cycling past the last race causes the list to "reset" and the current
 * race will be set to the first again.
 * <p>
 * This should be used for localised testing only.
 *
 * @author Barnesly
 */
public class DummyRaceService implements RaceService {

    private static final List<DisplayableRace> RACES;

    // Static initialiser for the {@code RACES} variable
    static {
        List<DisplayableRace> dummyRaces = new ArrayList<DisplayableRace>();
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 1, "M", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 2, "M", "SKUM 1", "Sheffield 1", "SKUM 1", null, null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 3, "M", "Sheffield 1", "Leeds 1", "Leeds 1", null, null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 4, "M", "SKUM 2", "Sheffield 2", "Sheffield 2", "final racer false start", null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 5, "L", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 6, "L", "Sheffield 1", "DUSSC 1", "Sheffield 1", null, null, false));
        dummyRaces.add(new DisplayableRace("Northern", "1", "Knockouts", 7, "B", "Sheffield 1", "Leeds 1", "Sheffield 1", null, null, false));

        RACES = Collections.unmodifiableList(dummyRaces);
    }

    private int dummyControl = 0;

    @Override
    public List<DisplayableRace> getRacesAll() {
        return cycleRaces();
    }

    @Override
    public List<DisplayableRace> getRacesByLeague(String league) {
        return cycleRaces();
    }

    @Override
    public List<DisplayableRace> getRacesByLeagueAndRound(String round, String division) {
        return cycleRaces();
    }

    @Override
    public List<DisplayableRace> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return cycleRaces();
    }

    /**
     * Main control method which increments the current race and sets
     * the winner for previous races
     *
     * @return the {@code RACES} list of {@link DisplayableRace}s while moving the
     * current race ahead one and setting the winner of the previous race
     */
    private List<DisplayableRace> cycleRaces() {

        dummyControl++;
        if (dummyControl > RACES.size()) {
            dummyControl = 1;
        }

        List<DisplayableRace> races = new ArrayList<DisplayableRace>();

        for (DisplayableRace race : RACES) {
            races.add(new DisplayableRace(race));
        }

        for (int i = dummyControl - 1; i < races.size(); i++) {
            races.get(i).setWinner(null);
            races.get(i).setTeamOneDsq(null);
            races.get(i).setTeamTwoDsq(null);
        }

        races.get(dummyControl - 1).setNext(true);

        return races;
    }

}
