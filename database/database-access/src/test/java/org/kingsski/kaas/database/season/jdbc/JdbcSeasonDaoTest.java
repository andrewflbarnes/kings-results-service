package org.kingsski.kaas.database.season.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.season.Season;
import org.kingsski.kaas.database.season.SeasonDao;
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
public class JdbcSeasonDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private SeasonDao seasonDao;
    private List<Season> expectedSeasons = new ArrayList<>();
    private Season expectedSeason = new Season();

    @Before
    public void setUp() {
        seasonDao = new JdbcSeasonDao(jdbcTemplate);
    }

    @Test
    public void getSeasons() {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(SeasonMapper.class)))
                .willReturn(expectedSeasons);

        final List<Season> actualSeasons = seasonDao.getSeasons();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(SeasonMapper.class));

        assertNotNull(actualSeasons);
        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void getSeasonById() {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(SeasonMapper.class)))
                .willReturn(expectedSeason);

        final Season actualSeason = seasonDao.getSeasonById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { id }), any(SeasonMapper.class));

        assertNotNull(actualSeason);
        assertEquals(expectedSeason, actualSeason);
    }

    @Test
    public void getSeasonByName() {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(SeasonMapper.class)))
                .willReturn(expectedSeason);

        final Season actualSeason = seasonDao.getSeasonByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { name }), any(SeasonMapper.class));

        assertNotNull(actualSeason);
        assertEquals(expectedSeason, actualSeason);
    }

    @Test
    public void getSeasonByCompetition() {
        final String name = "boom";
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(SeasonMapper.class)))
                .willReturn(expectedSeasons);

        final List<Season> actualSeasons = seasonDao.getSeasonsByCompetition(name);

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] { name }), any(SeasonMapper.class));

        assertNotNull(actualSeasons);
        assertEquals(expectedSeasons, actualSeasons);
    }

}
