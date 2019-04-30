package org.kingsski.kaas.service.scoreregional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ScoreRegionalServiceTest {

    @Mock
    private ScoreRegionalDao scoreRegionalDao;

    private ScoreRegionalService scoreRegionalService;

    @Before
    public void setUp() {
        scoreRegionalService = new ScoreRegionalService(scoreRegionalDao);
    }

    @Test
    public void getScoreRegionals() {
        final List<ScoreRegional> scoreRegionals = new ArrayList<>();
        given(scoreRegionalDao.getScoreRegionals()).willReturn(scoreRegionals);

        List<ScoreRegional> returnedScoreRegionals = scoreRegionalService.getScoreRegionals();

        assertEquals(scoreRegionals, returnedScoreRegionals);
        then(scoreRegionalDao).should(times(1)).getScoreRegionals();
    }
}
