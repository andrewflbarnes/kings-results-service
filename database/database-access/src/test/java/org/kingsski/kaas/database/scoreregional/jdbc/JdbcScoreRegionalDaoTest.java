package org.kingsski.kaas.database.scoreregional.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
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
public class JdbcScoreRegionalDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private ScoreRegionalDao scoreRegionalDao;
    private List<ScoreRegional> expectedScoreRegionals = new ArrayList<>();

    @Before
    public void setUp() {
        scoreRegionalDao = new JdbcScoreRegionalDao(jdbcTemplate);
    }

    @Test
    public void getScoreRegionals() {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(ScoreRegionalMapper.class)))
                .willReturn(expectedScoreRegionals);

        final List<ScoreRegional> actualScoreRegionals = scoreRegionalDao.getScoreRegionals();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(ScoreRegionalMapper.class));

        assertNotNull(actualScoreRegionals);
        assertEquals(expectedScoreRegionals, actualScoreRegionals);
    }

}
