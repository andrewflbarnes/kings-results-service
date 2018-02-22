package org.kingsski.api.dao.team;

import org.junit.Test;
import org.kingsski.api.dao.team.StubTeamDao;
import org.kingsski.api.model.Team;
import org.kingsski.api.dao.team.TeamDao;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StubTeamDaoTest {
    private static final TeamDao SERVICE = new StubTeamDao();

    @Test
    public void getTeamsAll() throws Exception {
        List<Team> teams = SERVICE.getTeamsAll();

        assertNotNull(teams);
        assertTrue(teams.isEmpty());
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        List<Team> teams = SERVICE.getTeamsByLeague("LEAGUE");
        List<Team> teams2 = SERVICE.getTeamsByLeague("OTHERLEAGUE");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        List<Team> teams = SERVICE.getTeamsByLeagueAndDivision("LEAGUE", "DIVISION");
        List<Team> teams2 = SERVICE.getTeamsByLeagueAndDivision("OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        List<Team> teams = SERVICE.getTeamsBySeasonAndLeagueAndDivision("SEASON", "LEAGUE", "DIVISION");
        List<Team> teams2 = SERVICE.getTeamsBySeasonAndLeagueAndDivision("OTHERSEASON", "OTHERLEAGUE", "OTHERDIVISION");

        assertNotNull(teams);
        assertTrue(teams.isEmpty());

        assertNotNull(teams2);
        assertTrue(teams2.isEmpty());

        assertEquals(teams, teams2);
    }

}