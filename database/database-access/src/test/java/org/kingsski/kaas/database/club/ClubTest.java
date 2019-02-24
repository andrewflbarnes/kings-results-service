package org.kingsski.kaas.database.club;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ClubTest {

    @Test
    public void equalityTests() {
        Club a = new Club().name("A").id(1).teamCount(1);
        Club a2 = new Club().name("A").id(1).teamCount(1);
        Club b = new Club().name("B").id(2).teamCount(2);
        Club c = new Club().name("C").id(1).teamCount(1);

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
        Club club = new Club().name("club").id(123).teamCount(456);
        String clubString = club.toString();
        assertTrue(clubString.contains("club"));
        assertTrue(clubString.contains("123"));
        assertTrue(clubString.contains("456"));
    }

}
