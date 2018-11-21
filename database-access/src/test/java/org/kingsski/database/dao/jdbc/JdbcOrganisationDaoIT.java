package org.kingsski.database.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.database.dao.OrganisationDao;
import org.kingsski.database.model.Organisation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcOrganisationDao} which are performed against a database
 */
public class JdbcOrganisationDaoIT extends AbstractDaoIT {

    private OrganisationDao organisationDao;

    @Before
    public void setUp() {
        organisationDao = new JdbcOrganisationDao(jdbcTemplate);
        clearDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    @Test
    public void getOrganisations() {
        List<String> organisationNames = new ArrayList<>();
        organisationNames.add("Organisation A");
        organisationNames.add("Organisation B");

        for (String organisation : organisationNames) {
            addOrganisationAndCompetitions(organisation, 0);
        }

        List<Organisation> organisations = organisationDao.getOrganisations();

        assertNotNull(organisations);
        assertEquals(2, organisations.size());
        assertTrue(organisationNames.contains(organisations.get(0).getName()));
        assertTrue(organisationNames.contains(organisations.get(1).getName()));
    }

    @Test
    public void getOrganisationById() {
        addOrganisationAndCompetitions("Organisation C", 3);

        List<Organisation> organisations = organisationDao.getOrganisations();

        assertNotNull(organisations);
        assertEquals(1, organisations.size());

        Organisation organisation = organisationDao.getOrganisationById(organisations.get(0).getId());

        assertNotNull(organisation);
        assertEquals(organisations.get(0), organisation);
    }

    @Test
    public void getOrganisationByIdNotExist() {
        Organisation organisation = organisationDao.getOrganisationById(9999);

        assertNull(organisation);
    }

    @Test
    public void getOrganisationByName() {
        final String organisationName = "Organisation D";
        addOrganisationAndCompetitions(organisationName, 4);

        Organisation organisation = organisationDao.getOrganisationByName(organisationName);

        assertNotNull(organisation);
        assertEquals(organisation.getName(), organisationName);
    }

    @Test
    public void getOrganisationByNameNotExist() {
        Organisation organisation = organisationDao.getOrganisationByName("Organisation does not exist");

        assertNull(organisation);
    }
}
