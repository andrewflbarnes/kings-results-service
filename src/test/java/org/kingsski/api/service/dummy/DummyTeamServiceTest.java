package org.kingsski.api.service.dummy;

import org.junit.Test;
import org.kingsski.api.model.DisplayableTeam;
import org.kingsski.api.service.TeamService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DummyTeamServiceTest {
    private static final TeamService SERVICE = new DummyTeamService();

    @Test
    public void getTeamsAll() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsAll();

        assertNotNull(teams);
        assertFalse(teams.isEmpty());
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsByLeague("LEAGUE");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsByLeague("OTHERLEAGUE");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsByLeagueAndDivision("LEAGUE", "DIVISION");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsByLeagueAndDivision("OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        List<DisplayableTeam> teams = SERVICE.getTeamsBySeasonAndLeagueAndDivision("SEASON", "LEAGUE", "DIVISION");
        List<DisplayableTeam> teams2 = SERVICE.getTeamsBySeasonAndLeagueAndDivision("OTHERSEASON", "OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

}