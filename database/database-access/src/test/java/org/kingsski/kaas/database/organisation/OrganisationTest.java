package org.kingsski.kaas.database.organisation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class OrganisationTest {

    @Test
    public void equalityTests() {
        Organisation.OrganisationBuilder builder = Organisation.builder();
        Organisation a = builder.name("A").id(1).competitionCount(1).build();
        Organisation a2 = builder.name("A").id(1).competitionCount(1).build();
        Organisation b = builder.name("B").id(2).competitionCount(2).build();
        Organisation c = builder.name("C").id(1).competitionCount(1).build();

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
        Organisation organisation = Organisation.builder().name("organisation").id(123).competitionCount(456).build();
        String organisationString = organisation.toString();
        assertTrue(organisationString.contains("organisation"));
        assertTrue(organisationString.contains("123"));
        assertTrue(organisationString.contains("456"));
    }

}
