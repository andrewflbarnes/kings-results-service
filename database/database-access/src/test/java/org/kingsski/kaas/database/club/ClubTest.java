package org.kingsski.kaas.database.club;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ClubTest {

    @Test
    public void equalityTests() {
        Club.ClubBuilder builder = Club.builder();
        Club a = builder.name("A").id(1).teamCount(1).build();
        Club a2 = builder.name("A").id(1).teamCount(1).build();
        Club b = builder.name("B").id(2).teamCount(2).build();
        Club c = builder.name("C").id(1).teamCount(1).build();

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
        Club.ClubBuilder builder = Club.builder();
        Club club = builder.name("club").id(123).teamCount(456).build();
        String clubString = club.toString();
        assertTrue(clubString.contains("club"));
        assertTrue(clubString.contains("123"));
        assertTrue(clubString.contains("456"));
    }

}
