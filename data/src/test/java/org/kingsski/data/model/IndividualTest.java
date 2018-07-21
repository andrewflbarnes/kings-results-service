package org.kingsski.data.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IndividualTest {

    private static final String MS = "Mens ski";
    private static final String LS = "Ladies ski";
    private static final String BATH = "BATH";
    private static final String SKUM = "SKUM";
    private static final String AIDAN = "Aidan Faria";
    private static final String JAMES = "James Dowdall";

    @Test
    public void testEquals() {
        Individual indi1A = createIndividual(1, MS, BATH, AIDAN, 1, 2, 1);
        Individual indi1B = createIndividual(2, MS, BATH, AIDAN, 1, 2, 1);
        Individual indi1C = createIndividual(1, MS, BATH, AIDAN, 2, 2, 1);
        Individual indi1D = createIndividual(1, MS, BATH, AIDAN, 1, 3, 1);
        Individual indi1E = createIndividual(1, MS, BATH, AIDAN, 1, 3, 2);

        assertEqualsAndHashCode(indi1A, indi1B);
        assertEqualsAndHashCode(indi1A, indi1C);
        assertEqualsAndHashCode(indi1A, indi1D);
        assertEqualsAndHashCode(indi1A, indi1E);

        Individual indi2 = createIndividual(1, LS, BATH, AIDAN, 1, 2, 1);
        assertNotEqualsAndHashCode(indi1A, indi2);

        Individual indi3 = createIndividual(1, MS, SKUM, AIDAN, 1, 2, 1);
        assertNotEqualsAndHashCode(indi1A, indi3);

        Individual indi4 = createIndividual(1, MS, BATH, JAMES, 1, 2, 1);
        assertNotEqualsAndHashCode(indi1A, indi4);
    }

    private static Individual createIndividual(int id, String discipline, String club, String name, int time1, int time2, int position) {
        Individual individual = new Individual();

        individual.setId(id);
        individual.setClub(club);
        individual.setDiscipline(discipline);
        individual.setName(name);
        individual.setTime1(time1);
        individual.setTime2(time2);
        individual.setTotalTime(time1 + time2);
        individual.setPosition(position);

        return individual;
    }

    private static void assertNotEqualsAndHashCode(Object expected, Object actual) {
        assertNotEquals(expected, actual);
        assertNotEquals(expected.hashCode(), actual.hashCode());
    }

    private static void assertEqualsAndHashCode(Object expected, Object actual) {
        assertEquals(expected, actual);
        assertEquals(expected.hashCode(), actual.hashCode());
    }
}
