package org.kingsski.kaas.database.organisation.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.kingsski.kaas.TestUtil.generatedKeyAnswer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JdbcOrganisationDaoTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    private OrganisationDao organisationDao;
    private List<Organisation> expectedOrganisations = new ArrayList<>();
    private Organisation expectedOrganisation = new Organisation();

    @Before
    public void setUp() {
        organisationDao = new JdbcOrganisationDao(jdbcTemplate);
    }

    @Test
    public void getOrganisations() {
        given(jdbcTemplate.query(any(String.class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisations);

        final List<Organisation> actualOrganisations = organisationDao.getOrganisations();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), any(OrganisationMapper.class));

        assertEquals(expectedOrganisations, actualOrganisations);
    }

    @Test
    public void getOrganisationById() {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisation);

        final Organisation actualOrganisation = organisationDao.getOrganisationById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(OrganisationMapper.class));

        assertEquals(expectedOrganisation, actualOrganisation);
    }

    @Test
    public void getOrganisationByName() {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(OrganisationMapper.class)))
                .willReturn(expectedOrganisation);

        final Organisation actualOrganisation = organisationDao.getOrganisationByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(OrganisationMapper.class));

        assertEquals(expectedOrganisation, actualOrganisation);
    }

    @Test
    public void addOrganisation() {
        final String name = "powpow";
        final long id = 13248L;

        given(jdbcTemplate.update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class)))
                .willAnswer(generatedKeyAnswer("organisation_id", id));

        final Organisation org = organisationDao.addOrganisation(name);

        then(jdbcTemplate).should(times(1))
                .update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class));

        assertNotNull(org);
        assertEquals(id, org.getId());
        assertEquals(name, org.getName());
    }
}
