package org.kingsski.kaas.service.season;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.season.Season;
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
@WebMvcTest(SeasonRestController.class)
public class SeasonRestControllerTest {

    private static final String API_SEASON = "/season/";
    private static final String API_SEASON_NAME = "/season?name=";
    private static final String API_SEASON_COMPETITION = "/season?competition=";

    @Resource
    private MockMvc mvc;

    @MockBean
    private SeasonService seasonService;

    @Test
    public void getSeasons() throws Exception {
        final Season season = new Season();
        season.setName("boom");
        final List<Season> seasons = new ArrayList<>();
        seasons.add(season);

        given(seasonService.getSeasons()).willReturn(seasons);

        mvc.perform(get(API_SEASON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(season.getName())));
    }

    @Test
    public void getSeasonByName() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Season season = new Season();
        season.setName(name);

        given(seasonService.getSeasonByName(name)).willReturn(season);
        given(seasonService.getSeasonByName(not(eq(name)))).willReturn(null);

        mvc.perform(get(API_SEASON_NAME + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(season.getName())));

        mvc.perform(get(API_SEASON_NAME + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSeasonByCompetition() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Season season = new Season();
        season.setName("popeye");
        final List<Season> seasons = new ArrayList<>();
        seasons.add(season);

        given(seasonService.getSeasonsByCompetition(name)).willReturn(seasons);
        given(seasonService.getSeasonsByCompetition(not(eq(name)))).willReturn(null);

        mvc.perform(get(API_SEASON_COMPETITION + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(season.getName())));

        mvc.perform(get(API_SEASON_COMPETITION + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSeasonById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Season season = new Season();
        season.setId(id);

        given(seasonService.getSeasonById(not(eq(id)))).willReturn(null);
        given(seasonService.getSeasonById(id)).willReturn(season);

        mvc.perform(get(API_SEASON + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)season.getId())));

        mvc.perform(get(API_SEASON + badId))
                .andExpect(status().isNotFound());
    }
}
