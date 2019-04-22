package org.kingsski.kaas.service.competition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.competition.Competition;
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
@WebMvcTest(CompetitionRestController.class)
public class CompetitionRestControllerTest {

    private static final String API_COMPETITIONS = "/competitions";
    private static final String API_COMPETITION_BY = "/competition/";

    @Resource
    private MockMvc mvc;

    @MockBean
    private CompetitionService competitionService;

    @Test
    public void getCompetitions() throws Exception {
        final Competition competition = new Competition();
        competition.name("boom");
        final List<Competition> competitions = new ArrayList<>();
        competitions.add(competition);

        given(competitionService.getCompetitions()).willReturn(competitions);

        mvc.perform(get(API_COMPETITIONS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(competition.getName())));
    }

    @Test
    public void getCompetitionByName() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Competition competition = new Competition();
        competition.name(name);

        given(competitionService.getCompetitionByName(not(eq(name)))).willReturn(null);
        given(competitionService.getCompetitionByName(name)).willReturn(competition);

        mvc.perform(get(API_COMPETITION_BY + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(competition.getName())));

        mvc.perform(get(API_COMPETITION_BY + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCompetitionById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Competition competition = new Competition();
        competition.id(id);

        given(competitionService.getCompetitionById(not(eq(id)))).willReturn(null);
        given(competitionService.getCompetitionById(id)).willReturn(competition);

        mvc.perform(get(API_COMPETITION_BY + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)competition.getId())));

        mvc.perform(get(API_COMPETITION_BY + badId))
                .andExpect(status().isNotFound());
    }
}
