package org.kingsski.kaas.database.division.jdbc;

import org.kingsski.kaas.database.division.Division;
import org.kingsski.kaas.database.division.DivisionDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link DivisionDao}
 */
public class JdbcDivisionDao implements DivisionDao {

    private static final String SELECT_ALL = "SELECT * FROM division";
    private static final String SELECT_BY_COMPETITION = "SELECT * FROM division WHERE competition = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM division WHERE division_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM division WHERE division = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcDivisionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Division> getDivisions() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new DivisionMapper());
    }

    @Override
    public List<Division> getDivisionsByCompetition(String competition) {
        return jdbcTemplate.query(SELECT_BY_COMPETITION, new Object[]{competition}, new DivisionMapper());
    }

    @Override
    public Division getDivisionById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new DivisionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Division getDivisionByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new DivisionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
