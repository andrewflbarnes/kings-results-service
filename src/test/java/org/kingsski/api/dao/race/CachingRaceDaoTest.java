package org.kingsski.api.dao.race;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.dao.race.CachingRaceDao;
import org.kingsski.api.dao.race.RaceDao;
import org.kingsski.api.model.Race;
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
public class CachingRaceDaoTest {

    private static final int INTERVAL = 100;
    private static final String LEAGUE = "league";
    private static final String ROUND = "round";
    private static final String SEASON = "season";

    private final Race raceMock = mock(Race.class);
    private final RaceDao raceDaoMock = mock(RaceDao.class);
    @Spy
    private final CachingRaceDao cachingRaceService = new CachingRaceDao(raceDaoMock);

    private final List<Race> raceListOne = new ArrayList<Race>() {{
        add(raceMock);
    }};
    private final List<Race> raceListTwo = new ArrayList<Race>() {{
        add(raceMock);
    }};

    @Before
    public void setUp() {
        cachingRaceService.setInterval(INTERVAL);
    }

    @Test
    public void getRacesAll() throws Exception {
        when(raceDaoMock.getRacesAll()).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<Race> races = cachingRaceService.getRacesAll();
        List<Race> races2 = cachingRaceService.getRacesAll();

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceDaoMock, times(1)).getRacesAll();

        Thread.sleep(INTERVAL*2);
        reset(raceDaoMock);
        when(raceDaoMock.getRacesAll()).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<Race> races3 = cachingRaceService.getRacesAll();
        List<Race> races4 = cachingRaceService.getRacesAll();

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceDaoMock, times(1)).getRacesAll();
    }

    @Test
    public void getRacesByLeague() throws Exception {
        when(raceDaoMock.getRacesByLeague(anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<Race> races = cachingRaceService.getRacesByLeague(LEAGUE);
        List<Race> races2 = cachingRaceService.getRacesByLeague(LEAGUE);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceDaoMock, times(1)).getRacesByLeague(LEAGUE);

        Thread.sleep(INTERVAL*2);
        reset(raceDaoMock);
        when(raceDaoMock.getRacesByLeague(anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<Race> races3 = cachingRaceService.getRacesByLeague(LEAGUE);
        List<Race> races4 = cachingRaceService.getRacesByLeague(LEAGUE);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceDaoMock, times(1)).getRacesByLeague(LEAGUE);
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        when(raceDaoMock.getRacesByLeagueAndRound(anyString(), anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<Race> races = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);
        List<Race> races2 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceDaoMock, times(1)).getRacesByLeagueAndRound(LEAGUE, ROUND);

        Thread.sleep(INTERVAL*2);
        reset(raceDaoMock);
        when(raceDaoMock.getRacesByLeagueAndRound(anyString(), anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<Race> races3 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);
        List<Race> races4 = cachingRaceService.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceDaoMock, times(1)).getRacesByLeagueAndRound(LEAGUE, ROUND);
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        when(raceDaoMock.getRacesBySeasonAndLeagueAndRound(anyString(), anyString(), anyString())).thenReturn(raceListOne).thenReturn(raceListTwo);

        List<Race> races = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
        List<Race> races2 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertNotNull(races);
        assertNotNull(races2);
        assertSame(races, races2);
        verify(raceDaoMock, times(1)).getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        Thread.sleep(INTERVAL*2);
        reset(raceDaoMock);
        when(raceDaoMock.getRacesBySeasonAndLeagueAndRound(anyString(), anyString(), anyString())).thenReturn(raceListTwo).thenReturn(raceListOne);

        List<Race> races3 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
        List<Race> races4 = cachingRaceService.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertNotNull(races3);
        assertNotNull(races4);
        assertSame(races3, races4);
        assertNotSame(races2, races3);
        verify(raceDaoMock, times(1)).getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);
    }

}