package org.kingsski.api.dao.jdbc;

import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTeamDao implements TeamDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTeamDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Team> getTeamsAll() {
        return jdbcTemplate.query("SELECT * FROM kings_teams", new Team.TeamMapper());
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        return jdbcTemplate.query("SELECT * FROM kings_teams WHERE league = ?",
                new Object[] { league },
                new Team.TeamMapper());
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return jdbcTemplate.query("SELECT * FROM kings_teams WHERE league = ? AND division = ?",
                new Object[] { league, division },
                new Team.TeamMapper());
    }

    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return jdbcTemplate.query("SELECT * FROM kings_teams WHERE league = ? AND division = ?",
                new Object[] { league, division },
                new Team.TeamMapper());
    }
}
