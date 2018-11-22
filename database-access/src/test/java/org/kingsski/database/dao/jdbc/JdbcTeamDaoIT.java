package org.kingsski.database.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.database.dao.TeamDao;
import org.kingsski.database.model.Team;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcTeamDao} which are performed against a database
 */
public class JdbcTeamDaoIT extends AbstractDaoIT {

    private TeamDao teamDao;

    @Before
    public void setUp() {
        teamDao = new JdbcTeamDao(jdbcTemplate);
        clearDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    @Test
    public void getTeams() {
        List<String> clubNames = new ArrayList<>();
        List<String> expectedTeams = new ArrayList<>();
        clubNames.add("Team A");
        clubNames.add("Team B");
        int teamCount = 2;

        int expectedTeamCount = 0;
        for (String team : clubNames) {
            addClubAndTeams(team, teamCount);
            for (int i = 1; i <= teamCount; i++) {
                expectedTeams.add(team + " " + i);
                expectedTeamCount++;
            }
        }

        List<Team> teams = teamDao.getTeams();

        assertNotNull(teams);
        assertEquals(expectedTeamCount, teams.size());
        for (Team team : teams) {
            assertTrue(expectedTeams.contains(team.getName()));
        }
    }

    @Test
    public void getTeamById() {
        addClubAndTeams("Team C", 3);

        List<Team> teams = teamDao.getTeams();

        assertNotNull(teams);
        assertEquals(3, teams.size());

        for (Team team : teams) {
            Team teamById = teamDao.getTeamById(team.getId());
            assertNotNull(teamById);
            assertEquals(team, teamById);
        }
    }

    @Test
    public void getTeamByIdNotExist() {
        Team team = teamDao.getTeamById(9999);

        assertNull(team);
    }

    @Test
    public void getTeamByName() {
        final String clubName = "Team D";
        final int teamCount = 4;
        addClubAndTeams(clubName, teamCount);

        for (int i = 1; i <= teamCount; i++) {
            Team team = teamDao.getTeamByName(clubName + " " + i);
            assertNotNull(team);
            assertEquals(team.getClub(), clubName);
        }
    }

    @Test
    public void getTeamByNameNotExist() {
        Team team = teamDao.getTeamByName("Team does not exist");

        assertNull(team);
    }
}
