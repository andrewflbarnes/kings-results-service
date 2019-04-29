package org.kingsski.kaas.database.season.jdbc;

import org.kingsski.kaas.database.season.Season;
import org.kingsski.kaas.database.season.SeasonDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link SeasonDao}
 */
public class JdbcSeasonDao implements SeasonDao {

    private static final String SELECT_ALL = "SELECT * FROM season";
    private static final String SELECT_BY_COMPETITION = "SELECT * FROM season WHERE competition = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM season WHERE season_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM season WHERE season = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcSeasonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Season> getSeasons() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new SeasonMapper());
    }

    @Override
    public List<Season> getSeasonsByCompetition(String competition) {
        return jdbcTemplate.query(SELECT_BY_COMPETITION, new Object[]{competition}, new SeasonMapper());
    }

    @Override
    public Season getSeasonById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new SeasonMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Season getSeasonByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new SeasonMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
