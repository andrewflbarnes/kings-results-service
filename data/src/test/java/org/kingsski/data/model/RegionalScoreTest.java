package org.kingsski.data.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class RegionalScoreTest {

    private static final String REGIONAL_1 = "regional1";
    private static final String REGIONAL_2 = "regional2";
    private static final int SCORE_1 = 1;
    private static final int SCORE_2 = 2;

    @Test
    public void testEquals() {
        RegionalScore rScore = new RegionalScore(REGIONAL_1, SCORE_1);
        RegionalScore rScore2 = new RegionalScore(REGIONAL_1, SCORE_1);
        RegionalScore rScore3 = new RegionalScore(REGIONAL_1, SCORE_2);
        RegionalScore rScore4 = new RegionalScore(REGIONAL_2, SCORE_1);
        RegionalScore rScore5 = new RegionalScore();

        assertNotEquals(rScore, null);
        assertNotEquals(null, rScore);

        assertEquals(rScore, rScore);
        assertEquals(rScore.hashCode(), rScore.hashCode());

        assertEquals(rScore, rScore2);
        assertEquals(rScore2, rScore);
        assertEquals(rScore.hashCode(), rScore2.hashCode());

        assertEquals(rScore, rScore3);
        assertEquals(rScore3, rScore);
        assertEquals(rScore.hashCode(), rScore3.hashCode());

        assertNotEquals(rScore, rScore4);
        assertNotEquals(rScore4, rScore);
        assertNotEquals(rScore.hashCode(), rScore4.hashCode());

        assertNotEquals(rScore, rScore5);
        assertNotEquals(rScore5, rScore);
        assertNotEquals(rScore.hashCode(), rScore5.hashCode());
    }

    @Test
    public void compareTo() {
        RegionalScore rScore = new RegionalScore(REGIONAL_1, SCORE_1);
        RegionalScore rScore2 = new RegionalScore(REGIONAL_1, SCORE_1);
        RegionalScore rScore3 = new RegionalScore(REGIONAL_1, SCORE_2);
        RegionalScore rScore4 = new RegionalScore(REGIONAL_2, SCORE_1);
        RegionalScore rScore5 = new RegionalScore();

        assertTrue(rScore.compareTo(null) < 0);

        assertTrue(rScore.compareTo(rScore) == 0);

        assertTrue(rScore.compareTo(rScore2) == 0);
        assertTrue(rScore2.compareTo(rScore) == 0);

        assertTrue(rScore.compareTo(rScore3) == 0);
        assertTrue(rScore3.compareTo(rScore) == 0);

        assertTrue(rScore.compareTo(rScore4) < 0);
        assertTrue(rScore4.compareTo(rScore) > 0);
    }

}