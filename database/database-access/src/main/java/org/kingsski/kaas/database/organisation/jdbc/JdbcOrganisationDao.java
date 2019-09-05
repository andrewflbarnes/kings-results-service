package org.kingsski.kaas.database.organisation.jdbc;

import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * JDBC implementation of {@link OrganisationDao}
 */
public class JdbcOrganisationDao implements OrganisationDao {

    private static final String SELECT_ALL = "SELECT * FROM organisation";
    private static final String SELECT_BY_ID = "SELECT * FROM organisation WHERE organisation_id = :id";
    private static final String SELECT_BY_NAME = "SELECT * FROM organisation WHERE name = :name";
    private static final String ADD_ORG = "INSERT INTO t_organisation(name) VALUES(:name)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcOrganisationDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Organisation> getOrganisations() {
        return jdbcTemplate.query(SELECT_ALL, new OrganisationMapper());
    }

    @Override
    public Organisation getOrganisationById(long id) {
        final Organisation org = Organisation.builder().id(id).build();
        final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(org);
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, params, new OrganisationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Organisation getOrganisationByName(String name) {
        final Organisation org = Organisation.builder().name(name).build();
        final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(org);
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, params, new OrganisationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Organisation addOrganisation(String name) {
        final Organisation org = Organisation.builder().name(name).build();
        final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(org);
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(ADD_ORG, params, keyHolder);

        org.setId((long)keyHolder.getKeys().get("organisation_id"));

        return org;
    }
}
