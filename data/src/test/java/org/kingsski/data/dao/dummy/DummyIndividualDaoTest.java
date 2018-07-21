package org.kingsski.data.dao.dummy;

import org.junit.Test;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.model.Individual;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DummyIndividualDaoTest {
    private static final IndividualDao SERVICE = new DummyIndividualDao();

    @Test
    public void testGetAllIndividuals() throws Exception {
        List<Individual> individuals = SERVICE.getAllIndividuals();

        assertNotNull(individuals);
        assertFalse(individuals.isEmpty());
    }

    @Test
    public void testGetIndividualsByClub() throws Exception {
        List<Individual> individuals = SERVICE.getIndividualsByClub("CLUB1");
        List<Individual> individuals2 = SERVICE.getIndividualsByClub("CLUB2");

        assertNotNull(individuals);
        assertFalse(individuals.isEmpty());

        assertNotNull(individuals2);
        assertFalse(individuals2.isEmpty());

        assertNotNull(individuals2);
        assertFalse(individuals2.isEmpty());

        assertEquals(individuals, individuals2);
    }

    @Test
    public void testGetIndividualsByDiscipline() throws Exception {
        List<Individual> individuals = SERVICE.getIndividualsByDiscipline("DISC1");
        List<Individual> individuals2 = SERVICE.getIndividualsByDiscipline("DISC2");

        assertNotNull(individuals);
        assertFalse(individuals.isEmpty());

        assertNotNull(individuals2);
        assertFalse(individuals2.isEmpty());

        assertEquals(individuals, individuals2);
    }
}