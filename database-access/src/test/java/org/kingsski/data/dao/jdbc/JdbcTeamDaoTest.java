package org.kingsski.data.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.TeamDao;
import org.kingsski.data.dao.jdbc.mapper.TeamMapper;
import org.kingsski.data.model.Team;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JdbcTeamDaoTest {

    private static final String STMT_ALL = "SA";
    private static final String STMT_LEAG = "SBL";
    private static final String STMT_LEDI = "SBLD";
    private static final String LEAGUE = "league";
    private static final String DIVISION = "division";
    private static final String SEASON = "season";

    @Mock
    private JdbcTemplate jdbcTemplate;

    private List<Team> teams = new ArrayList<>();

    private Map<String, String> jdbcStatements = new HashMap<>();

    @InjectMocks
    private TeamDao teamDao = new JdbcTeamDao(jdbcTemplate, jdbcStatements);

    @Test
    public void getTeamsAll() throws Exception {
        jdbcStatements.put(JdbcTeamDao.SELECT_ALL, STMT_ALL);
        when(jdbcTemplate.query(eq(STMT_ALL), any(TeamMapper.class)))
                .thenReturn(teams);

        List<Team> actual = teamDao.getTeamsAll();

        assertEquals(teams, actual);
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        jdbcStatements.put(JdbcTeamDao.SELECT_BY_LEAGUE, STMT_LEAG);
        when(jdbcTemplate.query(eq(STMT_LEAG), eq(new Object[] { LEAGUE }), any(TeamMapper.class)))
                .thenReturn(teams);

        List<Team> actual = teamDao.getTeamsByLeague(LEAGUE);

        assertEquals(teams, actual);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        jdbcStatements.put(JdbcTeamDao.SELECT_BY_LEAGUE_DIVISION, STMT_LEDI);
        when(jdbcTemplate.query(eq(STMT_LEDI), eq(new Object[] { LEAGUE, DIVISION }), any(TeamMapper.class)))
                .thenReturn(teams);

        List<Team> actual = teamDao.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertEquals(teams, actual);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        jdbcStatements.put(JdbcTeamDao.SELECT_BY_LEAGUE_DIVISION, STMT_LEDI);
        when(jdbcTemplate.query(eq(STMT_LEDI), eq(new Object[] { LEAGUE, DIVISION }), any(TeamMapper.class)))
                .thenReturn(teams);

        List<Team> actual = teamDao.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertEquals(teams, actual);
    }

}