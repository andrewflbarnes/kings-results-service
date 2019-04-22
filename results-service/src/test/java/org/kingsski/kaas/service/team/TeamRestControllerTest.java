package org.kingsski.kaas.service.team;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.team.Team;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamRestController.class)
public class TeamRestControllerTest {

    private static final String API_TEAMS = "/teams";
    private static final String API_TEAM_BY = "/team/";

    @Resource
    private MockMvc mvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void getTeams() throws Exception {
        final Team team = new Team();
        team.setName("boom");
        final List<Team> teams = new ArrayList<>();
        teams.add(team);

        given(teamService.getTeams()).willReturn(teams);

        mvc.perform(get(API_TEAMS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(team.getName())));
    }

    @Test
    public void getTeamByName() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Team team = new Team();
        team.setName(name);

        given(teamService.getTeamByName(not(eq(name)))).willReturn(null);
        given(teamService.getTeamByName(name)).willReturn(team);

        mvc.perform(get(API_TEAM_BY + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(team.getName())));

        mvc.perform(get(API_TEAM_BY + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTeamById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Team team = new Team();
        team.setId(id);

        given(teamService.getTeamById(not(eq(id)))).willReturn(null);
        given(teamService.getTeamById(id)).willReturn(team);

        mvc.perform(get(API_TEAM_BY + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)team.getId())));

        mvc.perform(get(API_TEAM_BY + badId))
                .andExpect(status().isNotFound());
    }
}
