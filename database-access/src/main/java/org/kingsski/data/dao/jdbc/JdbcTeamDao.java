package org.kingsski.data.dao.jdbc;

import org.kingsski.data.dao.TeamDao;
import org.kingsski.data.dao.jdbc.mapper.TeamMapper;
import org.kingsski.data.model.Team;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JdbcTeamDao extends AbstractJdbcDao implements TeamDao {

    public static final String SELECT_ALL = "jdbcTeamSelectAll";
    public static final String SELECT_BY_LEAGUE = "jdbcTeamSelectLeague";
    public static final String SELECT_BY_LEAGUE_DIVISION = "jdbcTeamSelectLeagueDivision";

    public JdbcTeamDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        super(jdbcTemplate, jdbcStatements);
    }

    @Override
    public List<Team> getTeamsAll() {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_ALL), new TeamMapper());
    }

    @Override
    public List<Team> getTeamsByLeague(String league) {
        List<Team> teams = jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE),
                new Object[] { league },
                new TeamMapper());
        List<Team> uniqueTeams = teams
                .stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        for (Team team : teams) {
            uniqueTeams.get(uniqueTeams.indexOf(team)).addScores(team.getScores());
        }

        return uniqueTeams;
    }

    @Override
    public List<Team> getTeamsByLeagueAndDivision(String league, String division) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_DIVISION),
                new Object[] { league, division },
                new TeamMapper());
    }

    //TODO Use the season to filter results
    @Override
    public List<Team> getTeamsBySeasonAndLeagueAndDivision(String season, String league, String division) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_LEAGUE_DIVISION),
                new Object[] { league, division },
                new TeamMapper());
    }
}
