package org.kingsski.api.dao.jdbc;

import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcTeamDao extends AbstractJdbcDao implements TeamDao {

    public static final String SELECT_ALL = "jdbcTeamSelectAll";
    public static final String SELECT_BY_LEAGUE = "jdbcTeamSelectLeague";
    public static final String SELECT_BY_LEAGUE_DIVISION = "jdbcTeamSelectLeagueDivision";

    public JdbcTeamDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        super(jdbcTemplate, jdbcStatements);
    }

    @Override
    public List<Team> getTeamsAll() {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_ALL), new Team.TeamMapper());
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE),
                new Object[] { league },
                new Team.TeamMapper());
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_DIVISION),
                new Object[] { league, division },
                new Team.TeamMapper());
    }

    //TODO Use the season to filter results
    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_DIVISION),
                new Object[] { league, division },
                new Team.TeamMapper());
    }
}
