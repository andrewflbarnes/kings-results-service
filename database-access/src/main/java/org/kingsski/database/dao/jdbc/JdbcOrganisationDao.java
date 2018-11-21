package org.kingsski.database.dao.jdbc;

import org.kingsski.database.dao.OrganisationDao;
import org.kingsski.database.dao.jdbc.mapper.OrganisationMapper;
import org.kingsski.database.model.Organisation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link OrganisationDao}
 */
public class JdbcOrganisationDao implements OrganisationDao {

    private static final String SELECT_ALL = "SELECT * FROM organisation";
    private static final String SELECT_BY_ID = "SELECT * FROM organisation WHERE organisation_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM organisation WHERE name = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcOrganisationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Organisation> getOrganisations() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new OrganisationMapper());
    }

    @Override
    public Organisation getOrganisationById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new OrganisationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Organisation getOrganisationByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new OrganisationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
