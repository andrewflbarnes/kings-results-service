package org.kingsski.database.organisation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class OrganisationTest {

    @Test
    public void equalityTests() {
        Organisation a = new Organisation().name("A").id(1).competitionCount(1);
        Organisation a2 = new Organisation().name("A").id(1).competitionCount(1);
        Organisation b = new Organisation().name("B").id(2).competitionCount(2);
        Organisation c = new Organisation().name("C").id(1).competitionCount(1);

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
        Organisation organisation = new Organisation().name("organisation").id(123).competitionCount(456);
        String organisationString = organisation.toString();
        assertTrue(organisationString.contains("organisation"));
        assertTrue(organisationString.contains("123"));
        assertTrue(organisationString.contains("456"));
    }

}
