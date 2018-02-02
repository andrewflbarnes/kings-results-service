package org.kingsski.api.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisplayableTeamTest {

    private static final String TEAM = "TEAM";
    private static final String LEAGUE = "LEAGUE";
    private static final String DIVISION = "DIVISION";
    private static final int POSITION = 1;
    private static final int SCORE_1 = 1;
    private static final int SCORE_2 = 2;
    private static final int SCORE_3 = 3;
    private static final int SCORE_4 = 4;
    private static final int SCORE_5 = 5;

    @Test
    public void testConstructor() {
        DisplayableTeam team = new DisplayableTeam(TEAM, LEAGUE, DIVISION, POSITION, SCORE_1, SCORE_2, SCORE_3, SCORE_4);

        assertEquals(TEAM, team.getTeam());
        assertEquals(LEAGUE, team.getLeague());
        assertEquals(DIVISION, team.getDivision());
        assertEquals(POSITION, team.getPosition());
        assertEquals(SCORE_1, team.getR1());
        assertEquals(SCORE_2, team.getR2());
        assertEquals(SCORE_3, team.getR3());
        assertEquals(SCORE_4, team.getR4());
        assertEquals(SCORE_1, team.getOrderedScore4());
        assertEquals(SCORE_2, team.getOrderedScore3());
        assertEquals(SCORE_3, team.getOrderedScore2());
        assertEquals(SCORE_4, team.getOrderedScore1());
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3 + SCORE_4, team.getTotal());
    }

    @Test
    public void testScoreUpdate() {
        DisplayableTeam team = new DisplayableTeam(TEAM, LEAGUE, DIVISION, POSITION, SCORE_1, 0, 0, 0);

        assertEquals(SCORE_1, team.getR1());
        assertEquals(0, team.getR2());
        assertEquals(0, team.getR3());
        assertEquals(0, team.getR4());
        assertEquals(0, team.getOrderedScore4());
        assertEquals(0, team.getOrderedScore3());
        assertEquals(0, team.getOrderedScore2());
        assertEquals(SCORE_1, team.getOrderedScore1());
        assertEquals(SCORE_1, team.getTotal());

        team.setR2(SCORE_2);
        assertEquals(SCORE_1, team.getR1());
        assertEquals(SCORE_2, team.getR2());
        assertEquals(0, team.getR3());
        assertEquals(0, team.getR4());
        assertEquals(0, team.getOrderedScore4());
        assertEquals(0, team.getOrderedScore3());
        assertEquals(SCORE_1, team.getOrderedScore2());
        assertEquals(SCORE_2, team.getOrderedScore1());
        assertEquals(SCORE_1 + SCORE_2, team.getTotal());

        team.setR3(SCORE_3);
        assertEquals(SCORE_1, team.getR1());
        assertEquals(SCORE_2, team.getR2());
        assertEquals(SCORE_3, team.getR3());
        assertEquals(0, team.getR4());
        assertEquals(0, team.getOrderedScore4());
        assertEquals(SCORE_1, team.getOrderedScore3());
        assertEquals(SCORE_2, team.getOrderedScore2());
        assertEquals(SCORE_3, team.getOrderedScore1());
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3, team.getTotal());

        team.setR4(SCORE_4);
        assertEquals(SCORE_1, team.getR1());
        assertEquals(SCORE_2, team.getR2());
        assertEquals(SCORE_3, team.getR3());
        assertEquals(SCORE_4, team.getR4());
        assertEquals(SCORE_1, team.getOrderedScore4());
        assertEquals(SCORE_2, team.getOrderedScore3());
        assertEquals(SCORE_3, team.getOrderedScore2());
        assertEquals(SCORE_4, team.getOrderedScore1());
        assertEquals(SCORE_1 + SCORE_2 + SCORE_3 + SCORE_4, team.getTotal());

        team.setR1(SCORE_5);
        assertEquals(SCORE_5, team.getR1());
        assertEquals(SCORE_2, team.getR2());
        assertEquals(SCORE_3, team.getR3());
        assertEquals(SCORE_4, team.getR4());
        assertEquals(SCORE_2, team.getOrderedScore4());
        assertEquals(SCORE_3, team.getOrderedScore3());
        assertEquals(SCORE_4, team.getOrderedScore2());
        assertEquals(SCORE_5, team.getOrderedScore1());
        assertEquals(SCORE_2 + SCORE_3 + SCORE_4 + SCORE_5, team.getTotal());
    }

}