package org.kingsski.database.competition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CompetitionTest {

    @Test
    public void equalityTests() {
        Competition a = new Competition().name("A").id(1).organisation("X");
        Competition a2 = new Competition().name("A").id(1).organisation("X");
        Competition b = new Competition().name("B").id(2).organisation("Y");
        Competition c = new Competition().name("C").id(1).organisation("X");

        assertFalse(a.equals(null));
        assertFalse(a.equals("boom"));

        assertEquals(a, a2);
        assertEquals(a2, a);
        assertEquals(a.hashCode(), a2.hashCode());

        assertNotEquals(a, b);
        assertNotEquals(b, a);
        assertNotEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(c, a);
        assertNotEquals(a.hashCode(), c.hashCode());
    }

    @Test
    public void string() {
        final String competitionName = "aeuogewgow";
        final String organisationName = "vve5ujddth";
        final long id = 341213L;

        Competition competition = new Competition().name(competitionName).id(id).organisation(organisationName);
        String competitionString = competition.toString();
        assertTrue(competitionString.contains(competitionName));
        assertTrue(competitionString.contains(String.valueOf(id)));
        assertTrue(competitionString.contains(organisationName));
    }

}
