package org.kingsski.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.service.TeamService;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link TeamController}
 */
@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamServiceMock;

    private TeamController controller;

    @Before
    public void setUp() throws Exception {
        controller = new TeamController(teamServiceMock);
    }

    @Test
    public void testGetAllTeams() {
        controller.teams();

        verify(teamServiceMock, times(1)).getTeamsAll();
    }

    @Test
    public void testGetTeamsByLeague() {
        final String league = "LEAGUE";

        controller.teamsByLeague(league);

        verify(teamServiceMock, times(1)).getTeamsByLeague(league);
    }

    @Test
    public void testGetTeamsByLeagueAndRound() {
        final String league = "LEAGUE";
        final String division = "DIVISION";

        controller.teamsByLeagueByDivision(league, division);

        verify(teamServiceMock, times(1)).getTeamsByLeagueAndDivision(league, division);
    }

    @Test
    public void testGetHistoricTeams() {
        final String league = "LEAGUE";
        final String season = "SEASON";
        final String division = "DIVISION";

        controller.teamsBySeasonByLeagueByDivision(season, league, division);

        verify(teamServiceMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(season, league, division);
    }
}
