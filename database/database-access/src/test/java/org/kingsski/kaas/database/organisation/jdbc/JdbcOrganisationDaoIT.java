package org.kingsski.kaas.database.organisation.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.kaas.database.jdbc.AbstractJdbcDaoIT;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcOrganisationDao} which are performed against a database
 */
public class JdbcOrganisationDaoIT extends AbstractJdbcDaoIT {

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
            addOrganisation(organisation);
        }

        List<Organisation> organisations = organisationDao.getOrganisations();

        assertNotNull(organisations);
        assertEquals(2, organisations.size());

        for (Organisation organisation : organisations) {
            assertTrue(organisationNames.contains(organisation.getName()));
        }
    }

    @Test
    public void getOrganisationById() {
        addOrganisation("Organisation C", 3);

        // DUMB - but we need a way to find the IDs
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
        addOrganisation(organisationName, 0);

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
