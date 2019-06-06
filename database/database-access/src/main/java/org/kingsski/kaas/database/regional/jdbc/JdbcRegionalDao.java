package org.kingsski.kaas.database.regional.jdbc;

import org.kingsski.kaas.database.regional.Regional;
import org.kingsski.kaas.database.regional.RegionalDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link RegionalDao}
 */
public class JdbcRegionalDao implements RegionalDao {

    private static final String SELECT_CLAUSE = "SELECT regional_id AS id, regional AS name, * FROM regional";

    private static final String SELECT_ALL = SELECT_CLAUSE;
    private static final String SELECT_BY_COMPETITION = SELECT_CLAUSE + " WHERE competition = ?";
    private static final String SELECT_BY_ID = SELECT_CLAUSE + " WHERE regional_id = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcRegionalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Regional> getRegionals() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new BeanPropertyRowMapper<>(Regional.class));
    }

    @Override
    public List<Regional> getRegionalsByCompetition(String competition) {
        return jdbcTemplate.query(SELECT_BY_COMPETITION, new Object[]{competition}, new BeanPropertyRowMapper<>(Regional.class));
    }

    @Override
    public Regional getRegionalById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Regional.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
