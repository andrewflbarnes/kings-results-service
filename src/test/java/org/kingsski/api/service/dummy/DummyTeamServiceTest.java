package org.kingsski.api.service.dummy;

import org.junit.Test;
import org.kingsski.api.service.TeamService;
import org.kingsski.wax.data.Team;

import java.util.List;

import static org.junit.Assert.*;

public class DummyTeamServiceTest {
    private static final TeamService SERVICE = new DummyTeamService();

    @Test
    public void getTeamsAll() throws Exception {
        List<Team> teams = SERVICE.getTeamsAll();

        assertNotNull(teams);
        assertFalse(teams.isEmpty());
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        List<Team> teams = SERVICE.getTeamsByLeague("LEAGUE");
        List<Team> teams2 = SERVICE.getTeamsByLeague("OTHERLEAGUE");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        List<Team> teams = SERVICE.getTeamsByLeagueAndDivision("LEAGUE", "DIVISION");
        List<Team> teams2 = SERVICE.getTeamsByLeagueAndDivision("OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        List<Team> teams = SERVICE.getTeamsBySeasonAndLeagueAndDivision("SEASON", "LEAGUE", "DIVISION");
        List<Team> teams2 = SERVICE.getTeamsBySeasonAndLeagueAndDivision("OTHERSEASON", "OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertFalse(teams.isEmpty());

        assertNotNull(teams2);
        assertFalse(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

}