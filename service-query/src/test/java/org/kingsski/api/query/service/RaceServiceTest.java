package org.kingsski.api.query.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.RaceDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link RaceService}
 */
@RunWith(MockitoJUnitRunner.class)
public class RaceServiceTest {

    @Mock
    private RaceDao raceDaoMock;

    private RaceService raceService;

    @Before
    public void setUp() throws Exception {
        raceService = new RaceService(raceDaoMock);
    }

    @Test
    public void testGetAllRaces() {
        raceService.getRacesAll();

        verify(raceDaoMock, times(1)).getRacesAll();
    }

    @Test
    public void testGetRacesByLeague() {
        final String league = "LEAGUE";

        raceService.getRacesByLeague(league);

        verify(raceDaoMock, times(1)).getRacesByLeague(league);
    }

    @Test
    public void testGetRacesByLeagueAndRound() {
        final String league = "LEAGUE";
        final String round = "ROUND";

        raceService.getRacesByLeagueAndRound(league, round);

        verify(raceDaoMock, times(1)).getRacesByLeagueAndRound(league, round);
    }

    @Test
    public void testGetHistoricRaces() {
        final String league = "LEAGUE";
        final String season = "SEASON";
        final String round = "ROUND";

        raceService.getRacesBySeasonAndLeagueAndRound(season, league, round);

        verify(raceDaoMock, times(1)).getRacesBySeasonAndLeagueAndRound(season, league, round);
    }
}
