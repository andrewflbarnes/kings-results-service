package org.kingsski.kaas.database.league.jdbc;

import org.kingsski.kaas.database.league.League;
import org.kingsski.kaas.database.league.LeagueDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link LeagueDao}
 */
public class JdbcLeagueDao implements LeagueDao {

    private static final String SELECT_ALL = "SELECT * FROM league";
    private static final String SELECT_BY_COMPETITION = "SELECT * FROM league WHERE competition = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM league WHERE league_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM league WHERE league = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcLeagueDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<League> getLeagues() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new LeagueMapper());
    }

    @Override
    public List<League> getLeaguesByCompetition(String competition) {
        return jdbcTemplate.query(SELECT_BY_COMPETITION, new Object[]{competition}, new LeagueMapper());
    }

    @Override
    public League getLeagueById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new LeagueMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public League getLeagueByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new LeagueMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
