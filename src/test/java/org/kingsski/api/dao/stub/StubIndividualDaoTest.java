package org.kingsski.api.dao.stub;

import org.junit.Test;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.model.Individual;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StubIndividualDaoTest {

    private static final IndividualDao SERVICE = new StubIndividualDao();

    @Test
    public void getIndividualsAll() throws Exception {
        List<Individual> individuals = SERVICE.getAllIndividuals();

        assertNotNull(individuals);
        assertTrue(individuals.isEmpty());
    }

    @Test
    public void getIndividualsByDiscipline() throws Exception {
        List<Individual> individuals = SERVICE.getIndividualsByDiscipline("");

        assertNotNull(individuals);
        assertTrue(individuals.isEmpty());
    }

    @Test
    public void getIndividualsByClub() throws Exception {
        List<Individual> individuals = SERVICE.getIndividualsByClub("");

        assertNotNull(individuals);
        assertTrue(individuals.isEmpty());
    }

}