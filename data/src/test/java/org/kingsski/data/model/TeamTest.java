package org.kingsski.data.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TeamTest {

    private static final String TEAM = "TEAM";
    private static final String CLUB = "CLUB";
    private static final String ORGANISATION = "Kings";
    private static final String COMPETITION = "Kings Ski";
    private static final String SEASON = "2017";
    private static final String LEAGUE = "LEAGUE";
    private static final String DIVISION = "DIVISION";
    private static final int POSITION = 1;
    private static final int SCORE_1 = 1;
    private static final int SCORE_2 = 2;
    private static final int SCORE_3 = 3;
    private static final int SCORE_4 = 4;
    private static final int SCORE_5 = 5;
    private static final String ROUND_1 = "ROUND_1";
    private static final String ROUND_2 = "ROUND_2";
    private static final String ROUND_3 = "ROUND_3";
    private static final String ROUND_4 = "ROUND_4";
    private static final String ROUND_5 = "ROUND_5";
    private static final RegionalScore R_SCORE1 = new RegionalScore(ROUND_1, SCORE_1);
    private static final RegionalScore R_SCORE2 = new RegionalScore(ROUND_2, SCORE_2);
    private static final RegionalScore R_SCORE3 = new RegionalScore(ROUND_3, SCORE_3);
    private static final RegionalScore R_SCORE4 = new RegionalScore(ROUND_4, SCORE_4);
    private static final RegionalScore R_SCORE5 = new RegionalScore(ROUND_5, SCORE_5);

    private final List<RegionalScore> SCORES = new ArrayList<>();

    @Before
    public void setUp() {
        SCORES.add(R_SCORE1);
        SCORES.add(R_SCORE2);
        SCORES.add(R_SCORE3);
        SCORES.add(R_SCORE4);
        SCORES.add(R_SCORE5);
    }

    @Test
    public void testEquals() {
        Team team = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertEqualsAndHashCode(team, team);
        // identical
        Team team2 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertEqualsAndHashCode(team, team2);
        // equal
        Team team3 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION + 1, SCORES);
        assertEqualsAndHashCode(team, team3);
        Team team4 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, null);
        assertEqualsAndHashCode(team, team4);
        // not equal
        Team team5 = new Team(CLUB + "1", TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team5);
        Team team6 = new Team(CLUB, TEAM + "1", ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team6);
        Team team7 = new Team(CLUB, TEAM, ORGANISATION + "1", COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team7);
        Team team8 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION + "1", SEASON, LEAGUE, DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team8);
        Team team9 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON + "1", LEAGUE, DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team9);
        Team team10 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE + "1", DIVISION, POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team10);
        Team team11 = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION + "1", POSITION, SCORES);
        assertNotEqualsAndHashCode(team, team11);
        // null
        assertNotEquals(team, null);
    }

    private void assertEqualsAndHashCode(Object expected, Object actual) {
        assertEquals(expected, actual);
        assertEquals(actual, expected);
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    private void assertNotEqualsAndHashCode(Object expected, Object actual) {
        assertNotEquals(expected, actual);
        assertNotEquals(actual, expected);
        assertNotEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    public void testConstructor() {
        Team team = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, SCORES);

        assertEquals(SCORE_1, team.getScore(0));
        assertEquals(SCORE_2, team.getScore(1));
        assertEquals(SCORE_3, team.getScore(2));
        assertEquals(SCORE_4, team.getScore(3));
        assertEquals(SCORE_5, team.getScore(4));
        assertEquals(SCORE_1, team.getScore(ROUND_1));
        assertEquals(SCORE_2, team.getScore(ROUND_2));
        assertEquals(SCORE_3, team.getScore(ROUND_3));
        assertEquals(SCORE_4, team.getScore(ROUND_4));
        assertEquals(SCORE_5, team.getScore(ROUND_5));
        assertEquals(SCORE_5, team.getOrderedScore(0));
        assertEquals(SCORE_4, team.getOrderedScore(1));
        assertEquals(SCORE_3, team.getOrderedScore(2));
        assertEquals(SCORE_2, team.getOrderedScore(3));
        assertEquals(SCORE_1, team.getOrderedScore(4));
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3 + SCORE_4 + SCORE_5, team.getTotal());

        List<RegionalScore> messyScores = new ArrayList<>();
        messyScores.add(new RegionalScore(ROUND_1, SCORE_5));
        messyScores.add(new RegionalScore(ROUND_2, SCORE_3));
        messyScores.add(new RegionalScore(ROUND_3, SCORE_1));
        messyScores.add(new RegionalScore(ROUND_4, SCORE_2));
        messyScores.add(new RegionalScore(ROUND_5, SCORE_4));

        team = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, messyScores);

        assertEquals(SCORE_5, team.getScore(0));
        assertEquals(SCORE_3, team.getScore(1));
        assertEquals(SCORE_1, team.getScore(2));
        assertEquals(SCORE_2, team.getScore(3));
        assertEquals(SCORE_4, team.getScore(4));
        assertEquals(SCORE_5, team.getScore(ROUND_1));
        assertEquals(SCORE_3, team.getScore(ROUND_2));
        assertEquals(SCORE_1, team.getScore(ROUND_3));
        assertEquals(SCORE_2, team.getScore(ROUND_4));
        assertEquals(SCORE_4, team.getScore(ROUND_5));
        assertEquals(SCORE_5, team.getOrderedScore(0));
        assertEquals(SCORE_4, team.getOrderedScore(1));
        assertEquals(SCORE_3, team.getOrderedScore(2));
        assertEquals(SCORE_2, team.getOrderedScore(3));
        assertEquals(SCORE_1, team.getOrderedScore(4));
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3 + SCORE_4 + SCORE_5, team.getTotal());
    }

    @Test
    public void testScoreUpdate() {
        List<RegionalScore> theseScores = new ArrayList<>();
        theseScores.add(new RegionalScore(ROUND_1, SCORE_1));

        Team team = new Team(CLUB, TEAM, ORGANISATION, COMPETITION, SEASON, LEAGUE, DIVISION, POSITION, theseScores);

        assertEquals(SCORE_1, team.getScore(ROUND_1));
        assertEquals(0, team.getScore(ROUND_2));
        assertEquals(0, team.getScore(ROUND_3));
        assertEquals(0, team.getScore(ROUND_4));
        assertEquals(0, team.getScore(ROUND_5));
        assertEquals(SCORE_1, team.getOrderedScore(0));
        assertEquals(0, team.getOrderedScore(1));
        assertEquals(0, team.getOrderedScore(2));
        assertEquals(0, team.getOrderedScore(3));
        assertEquals(0, team.getOrderedScore(4));
        assertEquals(SCORE_1, team.getTotal());

        team.setScore(ROUND_2, SCORE_2);
        assertEquals(SCORE_1, team.getScore(ROUND_1));
        assertEquals(SCORE_2, team.getScore(ROUND_2));
        assertEquals(0, team.getScore(ROUND_3));
        assertEquals(0, team.getScore(ROUND_4));
        assertEquals(0, team.getScore(ROUND_5));
        assertEquals(SCORE_2, team.getOrderedScore(0));
        assertEquals(SCORE_1, team.getOrderedScore(1));
        assertEquals(0, team.getOrderedScore(2));
        assertEquals(0, team.getOrderedScore(3));
        assertEquals(0, team.getOrderedScore(4));
        assertEquals(SCORE_1 + SCORE_2, team.getTotal());

        team.setScore(ROUND_3, SCORE_3);
        assertEquals(SCORE_1, team.getScore(ROUND_1));
        assertEquals(SCORE_2, team.getScore(ROUND_2));
        assertEquals(SCORE_3, team.getScore(ROUND_3));
        assertEquals(0, team.getScore(ROUND_4));
        assertEquals(0, team.getScore(ROUND_5));
        assertEquals(SCORE_3, team.getOrderedScore(0));
        assertEquals(SCORE_2, team.getOrderedScore(1));
        assertEquals(SCORE_1, team.getOrderedScore(2));
        assertEquals(0, team.getOrderedScore(3));
        assertEquals(0, team.getOrderedScore(4));
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3, team.getTotal());

        team.setScore(ROUND_4, SCORE_4);
        assertEquals(SCORE_1, team.getScore(ROUND_1));
        assertEquals(SCORE_2, team.getScore(ROUND_2));
        assertEquals(SCORE_3, team.getScore(ROUND_3));
        assertEquals(SCORE_4, team.getScore(ROUND_4));
        assertEquals(0, team.getScore(ROUND_5));
        assertEquals(SCORE_4, team.getOrderedScore(0));
        assertEquals(SCORE_3, team.getOrderedScore(1));
        assertEquals(SCORE_2, team.getOrderedScore(2));
        assertEquals(SCORE_1, team.getOrderedScore(3));
        assertEquals(0, team.getOrderedScore(4));
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3 + SCORE_4, team.getTotal());

        team.setScore(ROUND_1, SCORE_5);
        assertEquals(SCORE_5, team.getScore(ROUND_1));
        assertEquals(SCORE_2, team.getScore(ROUND_2));
        assertEquals(SCORE_3, team.getScore(ROUND_3));
        assertEquals(SCORE_4, team.getScore(ROUND_4));
        assertEquals(0, team.getScore(ROUND_5));
        assertEquals(SCORE_5, team.getOrderedScore(0));
        assertEquals(SCORE_4, team.getOrderedScore(1));
        assertEquals(SCORE_3, team.getOrderedScore(2));
        assertEquals(SCORE_2, team.getOrderedScore(3));
        assertEquals(0, team.getOrderedScore(4));
        assertEquals(SCORE_5 + SCORE_2 + SCORE_3 + SCORE_4, team.getTotal());
    }

}