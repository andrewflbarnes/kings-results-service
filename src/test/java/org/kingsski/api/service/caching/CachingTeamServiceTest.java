package org.kingsski.api.service.caching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.service.TeamService;
import org.kingsski.wax.data.Team;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
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

    private final Team teamMock = mock(Team.class);
    private final TeamService teamServiceMock = mock(TeamService.class);
    @Spy
    private final CachingTeamService cachingTeamService = new CachingTeamService(teamServiceMock);

    private final List<Team> teamListOne = new ArrayList<Team>() {{
        add(teamMock);
    }};
    private final List<Team> teamListTwo = new ArrayList<Team>() {{
        add(teamMock);
    }};

    @Before
    public void setUp() {
        cachingTeamService.setInterval(INTERVAL);
    }

    @Test
    public void getTeamsAll() throws Exception {
        when(teamServiceMock.getTeamsAll()).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<Team> teams = cachingTeamService.getTeamsAll();
        List<Team> teams2 = cachingTeamService.getTeamsAll();

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsAll();

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsAll()).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<Team> teams3 = cachingTeamService.getTeamsAll();
        List<Team> teams4 = cachingTeamService.getTeamsAll();

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsAll();
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        when(teamServiceMock.getTeamsByLeague(any())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<Team> teams = cachingTeamService.getTeamsByLeague(LEAGUE);
        List<Team> teams2 = cachingTeamService.getTeamsByLeague(LEAGUE);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsByLeague(LEAGUE);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsByLeague(any())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<Team> teams3 = cachingTeamService.getTeamsByLeague(LEAGUE);
        List<Team> teams4 = cachingTeamService.getTeamsByLeague(LEAGUE);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsByLeague(LEAGUE);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        when(teamServiceMock.getTeamsByLeagueAndDivision(any(), any())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<Team> teams = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
        List<Team> teams2 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsByLeagueAndDivision(any(), any())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<Team> teams3 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
        List<Team> teams4 = cachingTeamService.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsByLeagueAndDivision(LEAGUE, DIVISION);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        when(teamServiceMock.getTeamsBySeasonAndLeagueAndDivision(any(), any(), any())).thenReturn(teamListOne).thenReturn(teamListTwo);

        List<Team> teams = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
        List<Team> teams2 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertNotNull(teams);
        assertNotNull(teams2);
        assertSame(teams, teams2);
        verify(teamServiceMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        Thread.sleep(INTERVAL*2);
        reset(teamServiceMock);
        when(teamServiceMock.getTeamsBySeasonAndLeagueAndDivision(any(), any(), any())).thenReturn(teamListTwo).thenReturn(teamListOne);

        List<Team> teams3 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
        List<Team> teams4 = cachingTeamService.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertNotNull(teams3);
        assertNotNull(teams4);
        assertSame(teams3, teams4);
        assertNotSame(teams2, teams3);
        verify(teamServiceMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);
    }

}