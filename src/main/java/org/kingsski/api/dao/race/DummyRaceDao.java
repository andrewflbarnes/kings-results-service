package org.kingsski.api.dao.race;

import org.kingsski.api.model.Race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A dummy implementation of the {@link RaceDao}  interface. This class
 * has a single static list of {@link Race}s which it returns for all
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
public class DummyRaceDao implements RaceDao {

    private static final List<Race> RACES;
    private static final String NORTHERN = "Northern";
    private static final String ROUND = "1";
    private static final String KNOCKOUTS = "Knockouts";
    private static final String MIXED = "M";
    private static final String BOARD = "B";
    private static final String LADIES = "L";
    private static final String SKUM1 = "SKUM 1";
    private static final String LEEDS1 = "Leeds 1";
    private static final String SHEFF1 = "Sheffield 1";
    private static final String SKUM2 = "SKUM 2";
    private static final String SHEFF2 = "Sheffield 2";
    private static final String DUSSC1 = "DUSSC 1";

    // Static initialiser for the {@code RACES} variable
    static {
        List<Race> dummyRaces = new ArrayList<>();
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 1, MIXED, SKUM1, LEEDS1, SKUM1, null, null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 2, MIXED, SKUM1, SHEFF1, SKUM1, null, null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 3, MIXED, SHEFF1, LEEDS1, LEEDS1, null, null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 4, MIXED, SKUM2, SHEFF2, SHEFF2, "final racer false start", null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 5, LADIES, SKUM1, LEEDS1, SKUM1, null, null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 6, LADIES, SHEFF1, DUSSC1, SHEFF1, null, null, false));
        dummyRaces.add(new Race(NORTHERN, ROUND, KNOCKOUTS, 7, BOARD, SHEFF1, LEEDS1, SHEFF1, null, null, false));

        RACES = Collections.unmodifiableList(dummyRaces);
    }

    private int dummyControl = 0;

    @Override
    public List<Race> getRacesAll() {
        return cycleRaces();
    }

    @Override
    public List<Race> getRacesByLeague(String league) {
        return cycleRaces();
    }

    @Override
    public List<Race> getRacesByLeagueAndRound(String round, String division) {
        return cycleRaces();
    }

    @Override
    public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
        return cycleRaces();
    }

    /**
     * Main control method which increments the current race and sets
     * the winner for previous races
     *
     * @return the {@code RACES} list of {@link Race}s while moving the
     * current race ahead one and setting the winner of the previous race
     */
    private List<Race> cycleRaces() {

        dummyControl++;
        if (dummyControl > RACES.size()) {
            dummyControl = 1;
        }

        List<Race> races = new ArrayList<>();

        for (Race race : RACES) {
            races.add(new Race(race));
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
