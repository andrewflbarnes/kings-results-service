package org.kingsski.api.service.stub;

import org.junit.Test;
import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StubTeamServiceTest {
    private static final TeamService SERVICE = new StubTeamService();

    @Test
    public void getTeamsAll() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsAll();

        assertNotNull(teams);
        assertTrue(teams.isEmpty());
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsByLeague("LEAGUE");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsByLeague("OTHERLEAGUE");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsByLeagueAndDivision("LEAGUE", "DIVISION");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsByLeagueAndDivision("OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsBySeasonAndLeagueAndDivision("SEASON", "LEAGUE", "DIVISION");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsBySeasonAndLeagueAndDivision("OTHERSEASON", "OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

}