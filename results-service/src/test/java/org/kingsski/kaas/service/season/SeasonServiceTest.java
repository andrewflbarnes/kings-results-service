package org.kingsski.kaas.service.season;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.season.Season;
import org.kingsski.kaas.database.season.SeasonDao;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class SeasonServiceTest {

    @Mock
    private SeasonDao seasonDao;

    private SeasonService seasonService;

    @Before
    public void setUp() {
        seasonService = new SeasonService(seasonDao);
    }

    @Test
    public void getSeasons() {
        final List<Season> seasons = new ArrayList<>();
        given(seasonDao.getSeasons()).willReturn(seasons);

        List<Season> returnedSeasons = seasonService.getSeasons();

        assertEquals(seasons, returnedSeasons);
        then(seasonDao).should(times(1)).getSeasons();
    }

    @Test
    public void getSeasonByCompetition() {
        final List<Season> seasons = new ArrayList<>();
        final String competition = "competition";
        given(seasonDao.getSeasonsByCompetition(competition)).willReturn(seasons);

        List<Season> returnedSeasons = seasonService.getSeasonsByCompetition(competition);

        assertEquals(seasons, returnedSeasons);
        then(seasonDao).should(times(1)).getSeasonsByCompetition(competition);
    }

    @Test
    public void getSeasonByName() {
        final String name = "name";
        final Season season = new Season();
        given(seasonDao.getSeasonByName(name)).willReturn(season);

        Season returnedSeason = seasonService.getSeasonByName(name);

        assertEquals(season, returnedSeason);
        then(seasonDao).should(times(1)).getSeasonByName(name);
    }

    @Test
    public void getSeasonById() {
        final long id = 99L;
        final Season season = new Season();
        given(seasonDao.getSeasonById(id)).willReturn(season);

        Season returnedSeason = seasonService.getSeasonById(id);

        assertEquals(season, returnedSeason);
        then(seasonDao).should(times(1)).getSeasonById(id);
    }
}
