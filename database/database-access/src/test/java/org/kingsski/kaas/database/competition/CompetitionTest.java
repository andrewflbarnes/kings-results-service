package org.kingsski.kaas.database.competition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CompetitionTest {

    @Test
    public void equalityTests() {
        Competition.CompetitionBuilder builder = Competition.builder();
        Competition a = builder.name("A").id(1).organisation("X").build();
        Competition a2 = builder.name("A").id(1).organisation("X").build();
        Competition b = builder.name("B").id(2).organisation("Y").build();
        Competition c = builder.name("C").id(1).organisation("X").build();

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

        Competition competition = Competition.builder().name(competitionName).id(id).organisation(organisationName).build();
        String competitionString = competition.toString();
        assertTrue(competitionString.contains(competitionName));
        assertTrue(competitionString.contains(String.valueOf(id)));
        assertTrue(competitionString.contains(organisationName));
    }

}
