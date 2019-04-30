package org.kingsski.kaas.service.scoreregional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScoreRegionalRestController.class)
public class ScoreRegionalRestControllerTest {

    private static final String API_SCORE_REGIONAL = "/score/regional";

    @Resource
    private MockMvc mvc;

    @MockBean
    private ScoreRegionalService scoreRegionalService;

    @Test
    public void getScoreRegionals() throws Exception {
        final ScoreRegional scoreRegional = new ScoreRegional();
        scoreRegional.setRegional("boom");
        final List<ScoreRegional> scoreRegionals = new ArrayList<>();
        scoreRegionals.add(scoreRegional);

        given(scoreRegionalService.getScoreRegionals()).willReturn(scoreRegionals);

        mvc.perform(get(API_SCORE_REGIONAL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].regional", is(scoreRegional.getRegional())));
    }
}
