package org.kingsski.kaas.database.competition.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.jdbc.AbstractJdbcDaoIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        organisationNames.add("Organisation A");
        organisationNames.add("Organisation B");
        List<String> expectedCompetitions = new ArrayList<>();
        int competitionCount = 2;

        for (String organisation : organisationNames) {
            expectedCompetitions.addAll(
                    addOrganisation(organisation, competitionCount)
                            .keySet());
        }

        List<Competition> competitions = competitionDao.getCompetitions();

        assertNotNull(competitions);
        assertEquals(expectedCompetitions.size(), competitions.size());
        for (Competition competition : competitions) {
            assertTrue(expectedCompetitions.contains(competition.getName()));
        }
    }

    @Test
    public void getCompetitionById() {
        final String organisation = "Organisation C";
        addOrganisation(organisation, 3);

        // DUMB - but we need a way to find the IDs
        List<Competition> expectedCompetitions = competitionDao.getCompetitions().stream()
                .filter(c -> organisation.equals(c.getOrganisation()))
                .collect(Collectors.toList());

        for (Competition competition : expectedCompetitions ) {
            Competition competitionById = competitionDao.getCompetitionById(competition.getId());
            assertNotNull(competitionById);

            // Force IDs to match as we can't guarantee what it might be
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
        final String organisationName = "Organisation D";
        final int competitionCount = 4;

        Set<String> competitions = addOrganisation(organisationName, competitionCount).keySet();
        assertEquals(competitionCount, competitions.size());

        for (String competitionName : competitions) {
            Competition competition = competitionDao.getCompetitionByName(competitionName);
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
