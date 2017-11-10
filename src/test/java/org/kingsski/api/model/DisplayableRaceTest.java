package org.kingsski.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DisplayableRaceTest {

    private static final int NO_1 = 1;
    private static final int NO_2 = 2;
    private static final String TEAM_1 = "team1";
    private static final String TEAM_2 = "team2";
    private static final String TEAM_3 = "team3";
    private static final String DIV_M = "M";
    private static final String DIV_L = "L";
    private static final String DUMMY = "DUMMY";
    private static final String LEAGUE_N = "Northern";
    private static final String LEAGUE_S = "Southern";
    private static final String RND_1 = "1";
    private static final String RND_2 = "2";
    private static final String SET_1 = "1";
    private static final String SET_K = "Knockouts";

    // Base race
    private static final DisplayableRace RACE_1A = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // Identical to 1A
    private static final DisplayableRace RACE_1B = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // Identical to 1A other than transient fields
    private static final DisplayableRace RACE_1C = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, DUMMY, DUMMY, DUMMY, true);
    // Different league
    private static final DisplayableRace RACE_1U = new DisplayableRace(LEAGUE_S, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // Different round
    private static final DisplayableRace RACE_1V = new DisplayableRace(LEAGUE_N, RND_2, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // Different set
    private static final DisplayableRace RACE_1W = new DisplayableRace(LEAGUE_N, RND_1, SET_1, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // Different division
    private static final DisplayableRace RACE_1X = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_L, TEAM_1, TEAM_2, null, null, null, false);
    // Different team 1
    private static final DisplayableRace RACE_1Y = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_3, TEAM_2, null, null, null, false);
    // Different team 2
    private static final DisplayableRace RACE_1Z = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_3, null, null, null, false);
    // Different race number
    private static final DisplayableRace RACE_2A = new DisplayableRace(LEAGUE_N, RND_1, SET_K, NO_2, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
    // all null
    private static final DisplayableRace RACE_NULL = new DisplayableRace(null, null, null, 0, null, null, null, null, null, null, false);

    @Test
    public void testConstructor() {
        final String winner = "WINNER";
        final String dsq1 = "DSQ 1";
        final String dsq2 = "DSQ_2";
        final boolean next = true;

        DisplayableRace race = new DisplayableRace(LEAGUE_N, RND_1, SET_1, NO_1, DIV_L, TEAM_1, TEAM_2, winner, dsq1, dsq2, next);

        assertEquals(LEAGUE_N, race.getLeague());
        assertEquals(RND_1, race.getRound());
        assertEquals(SET_1, race.getSet());
        assertEquals(NO_1, race.getRaceNo());
        assertEquals(DIV_L, race.getDivision());
        assertEquals(TEAM_1, race.getTeamOne());
        assertEquals(TEAM_2, race.getTeamTwo());
        assertEquals(dsq1, race.getTeamOneDsq());
        assertEquals(dsq2, race.getTeamTwoDsq());
        assertEquals(winner, race.getWinner());
        assertEquals(next, race.isNext());
    }

    @Test
    public void testCopyOfConstructor() {
        DisplayableRace copy = new DisplayableRace(RACE_1C);

        assertEqualsAndHashCode(RACE_1C, copy);

        assertEquals(RACE_1C.getLeague(), copy.getLeague());
        assertEquals(RACE_1C.getRound(), copy.getRound());
        assertEquals(RACE_1C.getSet(), copy.getSet());
        assertEquals(RACE_1C.getRaceNo(), copy.getRaceNo());
        assertEquals(RACE_1C.getDivision(), copy.getDivision());
        assertEquals(RACE_1C.getTeamOne(), copy.getTeamOne());
        assertEquals(RACE_1C.getTeamTwo(), copy.getTeamTwo());
        assertEquals(RACE_1C.getTeamOneDsq(), copy.getTeamOneDsq());
        assertEquals(RACE_1C.getTeamTwoDsq(), copy.getTeamTwoDsq());
        assertEquals(RACE_1C.getWinner(), copy.getWinner());
        assertEquals(RACE_1C.isNext(), copy.isNext());
    }

    @Test
    public void testEquals() {
        assertEqualsAndHashCode(RACE_1A, RACE_1A);

        assertEqualsAndHashCode(RACE_1A, RACE_1B);
        assertEqualsAndHashCode(RACE_1B, RACE_1A);

        assertEqualsAndHashCode(RACE_1A, RACE_1C);
        assertEqualsAndHashCode(RACE_1C, RACE_1A);

        assertNotEquals(RACE_1A, null);
        assertNotEquals(null, RACE_1A);

        Object o = new Object();
        assertNotEqualsAndHashCode(RACE_1A, o);
        assertNotEqualsAndHashCode(o, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1U);
        assertNotEqualsAndHashCode(RACE_1U, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1V);
        assertNotEqualsAndHashCode(RACE_1V, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1W);
        assertNotEqualsAndHashCode(RACE_1W, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1X);
        assertNotEqualsAndHashCode(RACE_1X, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1Y);
        assertNotEqualsAndHashCode(RACE_1Y, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_1Z);
        assertNotEqualsAndHashCode(RACE_1Z, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_2A);
        assertNotEqualsAndHashCode(RACE_2A, RACE_1A);

        assertNotEqualsAndHashCode(RACE_1A, RACE_NULL);
        assertNotEqualsAndHashCode(RACE_NULL, RACE_1A);
    }

    private static void assertNotEqualsAndHashCode(Object expected, Object actual) {
        assertEqualsAndHashCode(expected, actual, false);
    }

    private static void assertEqualsAndHashCode(Object expected, Object actual) {
        assertEqualsAndHashCode(expected, actual, true);
    }

    private static void assertEqualsAndHashCode(Object expected, Object actual, boolean shouldBeEqual) {
        if (shouldBeEqual) {
            assertEquals(expected, actual);
            assertEquals(expected.hashCode(), actual.hashCode());
        } else {
            assertNotEquals(expected, actual);
            assertNotEquals(expected.hashCode(), actual.hashCode());
        }
    }
}
