package org.kingsski.kaas.database.competition.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
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
public class JdbcCompetitionDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private CompetitionDao competitionDao;
    private List<Competition> expectedCompetitions = new ArrayList<>();
    private Competition expectedCompetition = new Competition();

    @Before
    public void setUp() throws Exception {
        competitionDao = new JdbcCompetitionDao(jdbcTemplate);
    }

    @Test
    public void getCompetitions() throws Exception {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(CompetitionMapper.class)))
                .willReturn(expectedCompetitions);

        final List<Competition> actualCompetitions = competitionDao.getCompetitions();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(CompetitionMapper.class));

        assertNotNull(actualCompetitions);
        assertEquals(expectedCompetitions, actualCompetitions);
    }

    @Test
    public void getCompetitionById() throws Exception {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(CompetitionMapper.class)))
                .willReturn(expectedCompetition);

        final Competition actualCompetition = competitionDao.getCompetitionById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { id }), any(CompetitionMapper.class));

        assertNotNull(actualCompetition);
        assertEquals(expectedCompetition, actualCompetition);
    }

    @Test
    public void getCompetitionByName() throws Exception {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(CompetitionMapper.class)))
                .willReturn(expectedCompetition);

        final Competition actualCompetition = competitionDao.getCompetitionByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { name }), any(CompetitionMapper.class));

        assertNotNull(actualCompetition);
        assertEquals(expectedCompetition, actualCompetition);
    }

}
