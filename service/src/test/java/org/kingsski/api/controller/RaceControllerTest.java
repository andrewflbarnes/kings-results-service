package org.kingsski.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.service.RaceService;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link RaceController}
 */
@RunWith(MockitoJUnitRunner.class)
public class RaceControllerTest {

    @Mock
    private RaceService raceServiceMock;

    private RaceController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RaceController(raceServiceMock);
    }

    @Test
    public void testGetAllRaces() {
        controller.races();

        verify(raceServiceMock, times(1)).getRacesAll();
    }

    @Test
    public void testGetRacesByLeague() {
        final String league = "LEAGUE";

        controller.racesByLeague(league);

        verify(raceServiceMock, times(1)).getRacesByLeague(league);
    }

    @Test
    public void testGetRacesByLeagueAndRound() {
        final String league = "LEAGUE";
        final String round = "ROUND";

        controller.racesByLeagueByRound(league, round);

        verify(raceServiceMock, times(1)).getRacesByLeagueAndRound(league, round);
    }

    @Test
    public void testGetHistoricRaces() {
        final String league = "LEAGUE";
        final String season = "SEASON";
        final String round = "ROUND";

        controller.racesBySeasonByLeagueByRound(season, league, round);

        verify(raceServiceMock, times(1)).getRacesBySeasonAndLeagueAndRound(season, league, round);
    }
}
