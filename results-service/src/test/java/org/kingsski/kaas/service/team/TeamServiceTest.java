package org.kingsski.kaas.service.team;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class TeamServiceTest {

    @TestConfiguration
    static class TeamServiceTestConfiguration {
        @Bean
        public TeamService teamService() {
            return new TeamService();
        }
    }

    @MockBean
    private TeamDao teamDao;

    @Resource
    private TeamService teamService;

    @Test
    public void getTeams() {
        final List<Team> teams = new ArrayList<>();
        given(teamDao.getTeams()).willReturn(teams);

        List<Team> returnedTeams = teamService.getTeams();

        assertEquals(teams, returnedTeams);
        then(teamDao).should(times(1)).getTeams();
    }

    @Test
    public void getTeamByName() {
        final String name = "name";
        final Team team = new Team();
        given(teamDao.getTeamByName(name)).willReturn(team);

        Team returnedTeam = teamService.getTeamByName(name);

        assertEquals(team, returnedTeam);
        then(teamDao).should(times(1)).getTeamByName(name);
    }

    @Test
    public void getTeamById() {
        final long id = 99L;
        final Team team = new Team();
        given(teamDao.getTeamById(id)).willReturn(team);

        Team returnedTeam = teamService.getTeamById(id);

        assertEquals(team, returnedTeam);
        then(teamDao).should(times(1)).getTeamById(id);
    }
}