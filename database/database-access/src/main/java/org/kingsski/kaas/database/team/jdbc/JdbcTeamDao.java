package org.kingsski.kaas.database.team.jdbc;

import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * JDBC implementation of {@link TeamDao}
 */
public class JdbcTeamDao implements TeamDao {

    private static final String SELECT_ALL = "SELECT * FROM team";
    private static final String SELECT_BY_ID = "SELECT * FROM team WHERE team_id = :id";
    private static final String SELECT_BY_NAME = "SELECT * FROM team WHERE name = :name";
    private static final String ADD_TEAM = "INSERT INTO t_team(name, club_id) VALUES(:name, " +
            "(SELECT club_id FROM club WHERE name = :club))";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTeamDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Team> getTeams() {
        return jdbcTemplate.query(SELECT_ALL, new TeamMapper());
    }

    @Override
    public Team getTeamById(long id) {
        final Team team = Team.builder().id(id).build();
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_BY_ID,
                    new BeanPropertySqlParameterSource(team),
                    new TeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Team getTeamByName(String name) {
        final Team team = Team.builder().name(name).build();
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_BY_NAME,
                    new BeanPropertySqlParameterSource(team),
                    new TeamMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Team addTeam(String name, String club) {
        final Team team = Team.builder().name(name).club(club).build();
        final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(team);
        final KeyHolder keyholder = new GeneratedKeyHolder();

        jdbcTemplate.update(ADD_TEAM, params, keyholder);

        team.setId((long)keyholder.getKeys().get("team_id"));

        return team;
    }
}
