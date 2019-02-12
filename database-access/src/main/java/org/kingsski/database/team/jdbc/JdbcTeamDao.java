package org.kingsski.database.team.jdbc;

import org.kingsski.database.team.Team;
import org.kingsski.database.team.TeamDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link TeamDao}
 */
public class JdbcTeamDao implements TeamDao {

    private static final String SELECT_ALL = "SELECT * FROM team";
    private static final String SELECT_BY_ID = "SELECT * FROM team WHERE team_id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM team WHERE name = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Team> getTeams() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new TeamMapper());
    }

    @Override
    public Team getTeamById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, new Object[]{id}, new TeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Team getTeamByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_NAME, new Object[]{name}, new TeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
