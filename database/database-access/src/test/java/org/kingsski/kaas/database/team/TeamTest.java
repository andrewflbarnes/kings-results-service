package org.kingsski.kaas.database.team;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TeamTest {

    @Test
    public void equalityTests() {
        Team a = new Team().name("A").id(1).club("X");
        Team a2 = new Team().name("A").id(1).club("X");
        Team b = new Team().name("B").id(2).club("Y");
        Team c = new Team().name("C").id(1).club("X");

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
        final String teamName = "aeuogewgow";
        final String clubName = "vve5ujddth";
        final long id = 341213L;

        Team team = new Team().name(teamName).id(id).club(clubName);
        String teamString = team.toString();
        assertTrue(teamString.contains(teamName));
        assertTrue(teamString.contains(String.valueOf(id)));
        assertTrue(teamString.contains(clubName));
    }

}
