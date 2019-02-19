package org.kingsski.kaas.database.team.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JdbcTeamDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private TeamDao teamDao;
    private List<Team> expectedTeams = new ArrayList<>();
    private Team expectedTeam = new Team();

    @Before
    public void setUp() throws Exception {
        teamDao = new JdbcTeamDao(jdbcTemplate);
    }

    @Test
    public void getTeams() throws Exception {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(TeamMapper.class)))
                .willReturn(expectedTeams);

        final List<Team> actualTeams = teamDao.getTeams();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(TeamMapper.class));

        assertNotNull(actualTeams);
        assertEquals(expectedTeams, actualTeams);
    }

    @Test
    public void getTeamById() throws Exception {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(TeamMapper.class)))
                .willReturn(expectedTeam);

        final Team actualTeam = teamDao.getTeamById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { id }), any(TeamMapper.class));

        assertNotNull(actualTeam);
        assertEquals(expectedTeam, actualTeam);
    }

    @Test
    public void getTeamByName() throws Exception {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(TeamMapper.class)))
                .willReturn(expectedTeam);

        final Team actualTeam = teamDao.getTeamByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { name }), any(TeamMapper.class));

        assertNotNull(actualTeam);
        assertEquals(expectedTeam, actualTeam);
    }

}
