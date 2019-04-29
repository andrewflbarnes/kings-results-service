package org.kingsski.kaas.database.season.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.kaas.database.jdbc.AbstractJdbcDaoIT;
import org.kingsski.kaas.database.season.Season;
import org.kingsski.kaas.database.season.SeasonDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcSeasonDao} which are performed against a database
 */
public class JdbcSeasonDaoIT extends AbstractJdbcDaoIT {

    private SeasonDao seasonDao;

    @Before
    public void setUp() {
        seasonDao = new JdbcSeasonDao(jdbcTemplate);
        clearDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    @Test
    public void getSeasons() {
        List<String> organisationNames = new ArrayList<>();
        organisationNames.add("Organisation A");
        organisationNames.add("Organisation B");
        List<String> expectedSeasons = new ArrayList<>();

        for (String organisation : organisationNames) {
            Map<String, List<String>> organisationData = addOrganisation(organisation, 2, 2);
            for (List<String> seasons : organisationData.values()) {
                expectedSeasons.addAll(seasons);
            }
        }

        List<Season> seasons = seasonDao.getSeasons();

        assertNotNull(seasons);
        assertEquals(expectedSeasons.size(), seasons.size());
        for (Season season : seasons) {
            assertTrue(expectedSeasons.contains(season.getName()));
        }
    }

    @Test
    public void getSeasonById() {
        List<String> expectedSeasons = new ArrayList<>();

        for (List<String> seasons :
                addOrganisation("Organisation C", 1, 3).values()) {
            expectedSeasons.addAll(seasons);
        }

        List<Season> seasons = seasonDao.getSeasons();

        assertNotNull(seasons);
        assertEquals(expectedSeasons.size(), seasons.size());

        for (Season expectedSeason : seasons) {
            Season seasonById = seasonDao.getSeasonById(expectedSeason.getId());

            assertNotNull(seasonById);
            assertEquals(expectedSeason, seasonById);
        }

    }

    @Test
    public void getSeasonByIdNotExist() {
        Season season = seasonDao.getSeasonById(9999);

        assertNull(season);
    }

    @Test
    public void getSeasonByName() {
        List<String> expectedSeasons = new ArrayList<>();

        for (List<String> seasons :
                addOrganisation("Organisation D", 4, 2).values()) {
            expectedSeasons.addAll(seasons);
        }

        for (String seasonName : expectedSeasons) {
            Season seasonByName = seasonDao.getSeasonByName(seasonName);

            assertNotNull(seasonByName);
            assertEquals(seasonName, seasonByName.getName());
        }
    }

    @Test
    public void getSeasonByNameNotExist() {
        Season season = seasonDao.getSeasonByName("Season does not exist");

        assertNull(season);
    }
}
