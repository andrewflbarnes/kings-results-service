package org.kingsski.kaas.database.team;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TeamTest {

    @Test
    public void equalityTests() {
        Team.TeamBuilder builder = Team.builder();
        Team a = builder.name("A").id(1).club("X").build();
        Team a2 = builder.name("A").id(1).club("X").build();
        Team b = builder.name("B").id(2).club("Y").build();
        Team c = builder.name("C").id(1).club("X").build();

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

        Team team = Team.builder().name(teamName).id(id).club(clubName).build();
        String teamString = team.toString();
        assertTrue(teamString.contains(teamName));
        assertTrue(teamString.contains(String.valueOf(id)));
        assertTrue(teamString.contains(clubName));
    }

}
