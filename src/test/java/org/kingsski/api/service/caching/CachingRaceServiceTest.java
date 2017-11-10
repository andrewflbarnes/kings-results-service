package org.kingsski.api.service.caching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CachingRaceServiceTest {

    private static final int INTERVAL = 100;
    private static final String LEAGUE = "league";
    private static final String ROUND = "round";
    private static final String SEASON = "season";

    private final DisplayableRace raceMock = mock(DisplayableRace.class);
    private final RaceService raceServiceMock = mock(RaceService.class);
    @Spy
    private final CachingRaceService cachingRaceService = new CachingRaceService(raceServiceMock);

    private final List<DisplayableRace> raceListOne = new ArrayList<DisplayableRace>() {{
        add(raceMock);
    }};
    private final List<DisplayableRace> raceListTwo = new ArrayList<DisplayableRace>() {{
        add(raceMock);
    }};

    @Before
    public void setUp() {
        cachingRaceService.setInterval(INTERVAL);
    }

    @Test
    public void getRacesAll() throws Exception {
        when(raceServiceMock.getRacesAll()).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<DisplayableRace> races = cachingRaceService.getRacesAll();
        List<DisplayableRace> races2 = cachingRaceService.getRacesAll();

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceServiceMock, times(1)).getRacesAll();

        Thread.sleep(INTERVAL*2);
        reset(raceServiceMock);
        when(raceServiceMock.getRacesAll()).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<DisplayableRace> races3 = cachingRaceService.getRacesAll();
        List<DisplayableRace> races4 = cachingRaceService.getRacesAll();

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceServiceMock, times(1)).getRacesAll();
    }

    @Test
    public void getRacesByLeague() throws Exception {
        when(raceServiceMock.getRacesByLeague(anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<DisplayableRace> races = cachingRaceService.getRacesByLeague(LEAGUE);
        List<DisplayableRace> races2 = cachingRaceService.getRacesByLeague(LEAGUE);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceServiceMock, times(1)).getRacesByLeague(LEAGUE);

        Thread.sleep(INTERVAL*2);
        reset(raceServiceMock);
        when(raceServiceMock.getRacesByLeague(anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<DisplayableRace> races3 = cachingRaceService.getRacesByLeague(LEAGUE);
        List<DisplayableRace> races4 = cachingRaceService.getRacesByLeague(LEAGUE);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceServiceMock, times(1)).getRacesByLeague(LEAGUE);
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        when(raceServiceMock.getRacesByLeagueAndRound(anyString(), anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<DisplayableRace> races = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);
        List<DisplayableRace> races2 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceServiceMock, times(1)).getRacesByLeagueAndRound(LEAGUE, ROUND);

        Thread.sleep(INTERVAL*2);
        reset(raceServiceMock);
        when(raceServiceMock.getRacesByLeagueAndRound(anyString(), anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<DisplayableRace> races3 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);
        List<DisplayableRace> races4 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceServiceMock, times(1)).getRacesByLeagueAndRound(LEAGUE, ROUND);
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        when(raceServiceMock.getRacesBySeasonAndLeagueAndRound(anyString(), anyString(), anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<DisplayableRace> races = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
        List<DisplayableRace> races2 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceServiceMock, times(1)).getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        Thread.sleep(INTERVAL*2);
        reset(raceServiceMock);
        when(raceServiceMock.getRacesBySeasonAndLeagueAndRound(anyString(), anyString(), anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<DisplayableRace> races3 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
        List<DisplayableRace> races4 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceServiceMock, times(1)).getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
    }

}