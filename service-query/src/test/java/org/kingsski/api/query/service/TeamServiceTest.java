package org.kingsski.api.query.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.TeamDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link TeamService}
 */
@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamDao teamDaoMock;

    private TeamService teamService;

    @Before
    public void setUp() throws Exception {
        teamService = new TeamService(teamDaoMock);
    }

    @Test
    public void testGetAllTeams() {
        teamService.getTeamsAll();

        verify(teamDaoMock, times(1)).getTeamsAll();
    }

    @Test
    public void testGetTeamsByLeague() {
        final String league = "LEAGUE";

        teamService.getTeamsByLeague(league);

        verify(teamDaoMock, times(1)).getTeamsByLeague(league);
    }

    @Test
    public void testGetTeamsByLeagueAndRound() {
        final String league = "LEAGUE";
        final String division = "DIVISION";

        teamService.getTeamsByLeagueAndDivision(league, division);

        verify(teamDaoMock, times(1)).getTeamsByLeagueAndDivision(league, division);
    }

    @Test
    public void testGetHistoricTeams() {
        final String league = "LEAGUE";
        final String season = "SEASON";
        final String division = "DIVISION";

        teamService.getTeamsBySeasonAndLeagueAndDivision(season, league, division);

        verify(teamDaoMock, times(1)).getTeamsBySeasonAndLeagueAndDivision(season, league, division);
    }
}
