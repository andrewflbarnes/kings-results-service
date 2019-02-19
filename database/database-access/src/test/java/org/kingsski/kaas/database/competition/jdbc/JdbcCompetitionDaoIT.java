package org.kingsski.kaas.database.competition.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.jdbc.AbstractJdbcDaoIT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcCompetitionDao} which are performed against a database
 */
public class JdbcCompetitionDaoIT extends AbstractJdbcDaoIT {

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
        organisationNames.add("Organisation A");
        organisationNames.add("Organisation B");
        int competitionCount = 2;

        int expectedCompetitionCount = 0;
        for (String organisation : organisationNames) {
            addOrganisationAndCompetitions(organisation, competitionCount);
            for (int i = 1; i <= competitionCount; i++) {
                expectedCompetitions.add(organisation + " competition" + i);
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
        final String organisation = "Organisation C";
        final int competitionCount = 3;
        addOrganisationAndCompetitions(organisation, competitionCount);

        // Kind of dumb - we rely on another operation to retrieve the IDs!
        List<Competition> expectedCompetitions = competitionDao.getCompetitions().stream()
                .filter(c -> organisation.equals(c.getOrganisation()))
                .collect(Collectors.toList());

        for (Competition competition : expectedCompetitions ) {
            Competition competitionById = competitionDao.getCompetitionById(competition.getId());
            assertNotNull(competitionById);
            // Force IDs to match as we can't guarantee what it might be
            assertEquals(competition.id(123), competitionById.id(123));
        }
    }

    @Test
    public void getCompetitionByIdNotExist() {
        Competition competition = competitionDao.getCompetitionById(9999);

        assertNull(competition);
    }

    @Test
    public void getCompetitionByName() {
        final String organisationName = "Organisation D";
        final int competitionCount = 4;
        addOrganisationAndCompetitions(organisationName, competitionCount);

        for (int i = 1; i <= competitionCount; i++) {
            Competition competition = competitionDao.getCompetitionByName(organisationName + " competition" + i);
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
