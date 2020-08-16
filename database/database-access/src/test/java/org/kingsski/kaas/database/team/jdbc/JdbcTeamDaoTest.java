package org.kingsski.kaas.database.team.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.TestUtil;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JdbcTeamDaoTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    private TeamDao teamDao;
    private List<Team> expectedTeams = new ArrayList<>();
    private Team expectedTeam = new Team();

    @Before
    public void setUp() {
        teamDao = new JdbcTeamDao(jdbcTemplate);
    }

    @Test
    public void getTeams() {
        given(jdbcTemplate.query(any(String.class), any(TeamMapper.class)))
                .willReturn(expectedTeams);

        final List<Team> actualTeams = teamDao.getTeams();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), any(TeamMapper.class));

        assertNotNull(actualTeams);
        assertEquals(expectedTeams, actualTeams);
    }

    @Test
    public void getTeamById() {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(TeamMapper.class)))
                .willReturn(expectedTeam);

        final Team actualTeam = teamDao.getTeamById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(TeamMapper.class));

        assertNotNull(actualTeam);
        assertEquals(expectedTeam, actualTeam);
    }

    @Test
    public void getTeamByName() {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(TeamMapper.class)))
                .willReturn(expectedTeam);

        final Team actualTeam = teamDao.getTeamByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(TeamMapper.class));

        assertNotNull(actualTeam);
        assertEquals(expectedTeam, actualTeam);
    }

    @Test
    public void addTeam() {
        final String name = "boom";
        final String club = "pow";
        final long id = 9876L;
        given(jdbcTemplate.update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class)))
                .willAnswer(generatedKeyAnswer(id));

        final Team actualTeam = teamDao.addTeam(name, club);

        then(jdbcTemplate).should(times(1))
                .update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class));

        assertNotNull(actualTeam);
        assertEquals(club, actualTeam.getClub());
        assertEquals(name, actualTeam.getName());
        assertEquals(id, actualTeam.getId());
    }

    private Answer<?> generatedKeyAnswer(long id) {
        return TestUtil.generatedKeyAnswer("team_id", id);
    }
}
