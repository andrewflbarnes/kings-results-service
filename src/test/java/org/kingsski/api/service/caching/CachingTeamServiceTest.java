package org.kingsski.api.service.caching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;
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
public class CachingTeamServiceTest {

    private static final int INTERVAL = 100;
    private static final String LEAGUE = "league";
    private static final String DIVISION = "division";
    private static final String SEASON = "season";

    private final DisplayableTeam teamMock = mock(DisplayableTeam.class);
    private final TeamService teamServiceMock = mock(TeamService.class);
    @Spy
    private final CachingTeamService cachingTeamService = new CachingTeamService(teamServiceMock);

    private final List<DisplayableTeam> teamListOne = new ArrayList<DisplayableTeam>() {{
        add(teamMock);
    }};
    private final List<DisplayableTeam> teamListTwo = new ArrayList<DisplayableTeam>() {{
        add(teamMock);
    }};

    @Before
    public void setUp() {
        cachingTeamService.setInterval(INTERVAL);
    }

    @Test
    public void getTeamsAll() throws Exception {
        when(teamServiceMock.getTeamsAll()).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<DisplayableTeam> teams = cachingTeamService.getTeamsAll();
        List<DisplayableTeam> teams2 = cachingTeamService.getTeamsAll();

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsAll();

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsAll()).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<DisplayableTeam> teams3 = cachingTeamService.getTeamsAll();
        List<DisplayableTeam> teams4 = cachingTeamService.getTeamsAll();

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsAll();
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        when(teamServiceMock.getTeamsByLeague(anyString())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<DisplayableTeam> teams = cachingTeamService.getTeamsByLeague(LEAGUE);
        List<DisplayableTeam> teams2 = cachingTeamService.getTeamsByLeague(LEAGUE);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsByLeague(LEAGUE);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsByLeague(anyString())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<DisplayableTeam> teams3 = cachingTeamService.getTeamsByLeague(LEAGUE);
        List<DisplayableTeam> teams4 = cachingTeamService.getTeamsByLeague(LEAGUE);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsByLeague(LEAGUE);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        when(teamServiceMock.getTeamsByLeagueAndDivision(anyString(), anyString())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<DisplayableTeam> teams = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
        List<DisplayableTeam> teams2 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsByLeagueAndDivision(anyString(), anyString())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<DisplayableTeam> teams3 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
        List<DisplayableTeam> teams4 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        when(teamServiceMock.getTeamsBySeasonAndLeagueAndDivision(anyString(), anyString(), anyString())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<DisplayableTeam> teams = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
        List<DisplayableTeam> teams2 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsBySeasonAndLeagueAndDivision(anyString(), anyString(), anyString())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<DisplayableTeam> teams3 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
        List<DisplayableTeam> teams4 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
    }

}