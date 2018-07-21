package org.kingsski.data.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.model.Individual;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc-config.xml")
public class JdbcIndividualDaoIT extends AbstractDaoIT {

    private static final String DELETE_ALL = "DELETE FROM kings_individuals";
    private static final int ID = 1;
    private static final String NAME = "David Hasselhoff";
    private static final String DISCIPLINE = "Mens Snowboard";
    private static final String CLUB = "Baywatch";
    private static final int POSITION = 2;
    private static final int TIME1 = 30;
    private static final int TIME2  = 31;
    private static final int TIME_TOTAL = 61;

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcStatements;

    private IndividualDao individualDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        individualDao = new JdbcIndividualDao(jdbcTemplate, jdbcStatements);
        jdbcTemplate.execute(DELETE_ALL);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute(DELETE_ALL);
    }

    @Test
    public void getAllIndividuals() throws Exception {
        Individual expectedIndividual = addDefaultIndividual();

        List<Individual> individuals = individualDao.getAllIndividuals();

        assertNotNull(individuals);
        assertEquals(1, individuals.size());
        assertIndividualEquals(expectedIndividual, individuals.get(0));
    }

    @Test
    public void getIndividualsByClub() throws Exception {
        Individual expectedIndividual = addDefaultIndividual();

        List<Individual> individuals = individualDao.getIndividualsByClub(CLUB);

        assertNotNull(individuals);
        assertEquals(1, individuals.size());
        assertIndividualEquals(expectedIndividual, individuals.get(0));

        individuals = individualDao.getIndividualsByClub("NOT A CLUB");

        assertNotNull(individuals);
        assertEquals(0, individuals.size());
    }

    @Test
    public void getIndividualsByDiscipline() throws Exception {
        Individual expectedIndividual = addDefaultIndividual();

        List<Individual> individuals = individualDao.getIndividualsByDiscipline(DISCIPLINE);

        assertNotNull(individuals);
        assertEquals(1, individuals.size());
        assertIndividualEquals(expectedIndividual, individuals.get(0));

        individuals = individualDao.getIndividualsByDiscipline("NOT A DISCIPLINE");

        assertNotNull(individuals);
        assertEquals(0, individuals.size());
    }

    private Individual addDefaultIndividual() {
        Individual individual = new Individual();
        individual.setId(ID);
        individual.setName(NAME);
        individual.setDiscipline(DISCIPLINE);
        individual.setClub(CLUB);
        individual.setPosition(POSITION);
        individual.setTime1(TIME1);
        individual.setTime2(TIME2);
        individual.setTotalTime(TIME_TOTAL);

        addIndividual(individual);

        return individual;
    }

    private void addIndividual(Individual individual) {
        jdbcTemplate.update(
                "INSERT INTO kings_individuals(" +
                        "id, name, discipline, club, position, time_one, time_two, total_time) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                individual.getId(),
                individual.getName(),
                individual.getDiscipline(),
                individual.getClub(),
                individual.getPosition(),
                individual.getTime1(),
                individual.getTime2(),
                individual.getTotalTime());
    }

    private void assertIndividualEquals(Individual expected, Individual actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDiscipline(), actual.getDiscipline());
        assertEquals(expected.getClub(), actual.getClub());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getTime1(), actual.getTime1());
        assertEquals(expected.getTime2(), actual.getTime2());
        assertEquals(expected.getTotalTime(), actual.getTotalTime());
    }
}