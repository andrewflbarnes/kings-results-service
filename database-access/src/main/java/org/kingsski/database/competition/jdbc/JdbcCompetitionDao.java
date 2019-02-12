package org.kingsski.database.competition.jdbc;

import org.kingsski.database.competition.Competition;
import org.kingsski.database.competition.CompetitionDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link CompetitionDao}
 */
public class JdbcCompetitionDao implements CompetitionDao {

    private static final String SELECT_ALL = "SELECT * FROM competition";
    private static final String SELECT_BY_ID = "SELECT * FROM competition WHERE competition_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM competition WHERE name = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcCompetitionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Competition> getCompetitions() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new CompetitionMapper());
    }

    @Override
    public Competition getCompetitionById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new CompetitionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Competition getCompetitionByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new CompetitionMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
