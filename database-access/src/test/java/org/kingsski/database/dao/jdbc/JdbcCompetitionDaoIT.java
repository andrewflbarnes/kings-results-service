package org.kingsski.database.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.database.dao.CompetitionDao;
import org.kingsski.database.model.Competition;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcCompetitionDao} which are performed against a database
 */
public class JdbcCompetitionDaoIT extends AbstractDaoIT {

    private CompetitionDao competitionDao;

    @Before
    public void setUp() {
        competitionDao = new JdbcCompetitionDao(jdbcTemplate);
        clearDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    @Test
    public void getCompetitions() {
        List<String> organisationNames = new ArrayList<>();
        List<String> expectedCompetitions = new ArrayList<>();
        organisationNames.add("Competition A");
        organisationNames.add("Competition B");
        int competitionCount = 2;

        int expectedCompetitionCount = 0;
        for (String competition : organisationNames) {
            addOrganisationAndCompetitions(competition, competitionCount);
            for (int i = 1; i <= competitionCount; i++) {
                expectedCompetitions.add(competition + " " + i);
                expectedCompetitionCount++;
            }
        }

        List<Competition> competitions = competitionDao.getCompetitions();

        assertNotNull(competitions);
        assertEquals(expectedCompetitionCount, competitions.size());
        for (Competition competition : competitions) {
            assertTrue(expectedCompetitions.contains(competition.getName()));
        }
    }

    @Test
    public void getCompetitionById() {
        addOrganisationAndCompetitions("Competition C", 3);

        List<Competition> competitions = competitionDao.getCompetitions();

        assertNotNull(competitions);
        assertEquals(3, competitions.size());

        for (Competition competition : competitions) {
            Competition competitionById = competitionDao.getCompetitionById(competition.getId());
            assertNotNull(competitionById);
            assertEquals(competition, competitionById);
        }
    }

    @Test
    public void getCompetitionByIdNotExist() {
        Competition competition = competitionDao.getCompetitionById(9999);

        assertNull(competition);
    }

    @Test
    public void getCompetitionByName() {
        final String organisationName = "Competition D";
        final int competitionCount = 4;
        addOrganisationAndCompetitions(organisationName, competitionCount);

        for (int i = 1; i <= competitionCount; i++) {
            Competition competition = competitionDao.getCompetitionByName(organisationName + " " + i);
            assertNotNull(competition);
            assertEquals(competition.getOrganisation(), organisationName);
        }
    }

    @Test
    public void getCompetitionByNameNotExist() {
        Competition competition = competitionDao.getCompetitionByName("Competition does not exist");

        assertNull(competition);
    }
}
