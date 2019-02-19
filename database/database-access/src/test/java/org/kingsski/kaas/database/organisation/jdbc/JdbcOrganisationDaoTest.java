package org.kingsski.kaas.database.organisation.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JdbcOrganisationDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private OrganisationDao organisationDao;
    private List<Organisation> expectedOrganisations = new ArrayList<>();
    private Organisation expectedOrganisation = new Organisation();

    @Before
    public void setUp() throws Exception {
        organisationDao = new JdbcOrganisationDao(jdbcTemplate);
    }

    @Test
    public void getOrganisations() throws Exception {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisations);

        final List<Organisation> actualOrganisations = organisationDao.getOrganisations();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(OrganisationMapper.class));

        assertNotNull(actualOrganisations);
        assertEquals(expectedOrganisations, actualOrganisations);
    }

    @Test
    public void getOrganisationById() throws Exception {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisation);

        final Organisation actualOrganisation = organisationDao.getOrganisationById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { id }), any(OrganisationMapper.class));

        assertNotNull(actualOrganisation);
        assertEquals(expectedOrganisation, actualOrganisation);
    }

    @Test
    public void getOrganisationByName() throws Exception {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisation);

        final Organisation actualOrganisation = organisationDao.getOrganisationByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { name }), any(OrganisationMapper.class));

        assertNotNull(actualOrganisation);
        assertEquals(expectedOrganisation, actualOrganisation);
    }

}
