package org.kingsski.kaas.database.team.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.exception.EntityAlreadyExistsException;
import org.kingsski.kaas.database.exception.EntityConstraintViolationException;
import org.kingsski.kaas.database.jdbc.AbstractJdbcDaoIT;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration tests for {@link JdbcTeamDao} which are performed against a database
 */
public class JdbcTeamDaoIT extends AbstractJdbcDaoIT {

    private TeamDao teamDao;

    @Before
    public void setUp() {
        teamDao = new JdbcTeamDao(namedParameterJdbcTemplate);
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
        clubNames.add("Club A");
        clubNames.add("Club B");
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
            assertTrue(clubNames.contains(team.getClub()));
        }
    }

    @Test
    public void getTeamById() {
        final String club = "Club C";
        final int teamCount = 3;
        addClubAndTeams(club, teamCount);

        // Kind of dumb - we rely on another operation to retrieve the IDs!
        List<Team> expectedTeams = teamDao.getTeams().stream()
                .filter(t -> club.equals(t.getClub()))
                .collect(Collectors.toList());

        for (Team team : expectedTeams) {
            Team teamById = teamDao.getTeamById(team.getId());
            assertNotNull(teamById);;
            // Force IDs to match as we can't guarantee what it might be
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
        final String clubName = "Club D";
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

    @Test
    public void addTeam() {
        final String name = "Team E";
        final String club = "Club E";
        addClubAndTeams(club, 0);
        Team team = teamDao.addTeam(name, club);
        Team teamCheck = teamDao.getTeamByName(name);

        assertNotNull(team);
        assertNotNull(teamCheck);
        assertEquals(team.getId(), teamCheck.getId());
        assertEquals(team.getName(), teamCheck.getName());
        assertEquals(team.getClub(), teamCheck.getClub());
    }

    // The way the DB is currently structured means we need to allow non-unique team names.
    // This causes an obvious issues as it is the only human-readable identifier so without
    // the context of which leagues a team is associated with we don't which is which.
    // The structure of the DB should probably be changes to consolidate both the team and
    // registration tables into a single table.
    @Test
    public void addAlreadyExistingTeam() {
        final String club = "Club F";
        final String name = "Team F";
        addClubAndTeams(club, 0);
        teamDao.addTeam(name, club);
        teamDao.addTeam(name, club);
    }

    @Test(expected = EntityConstraintViolationException.class)
    public void addTeamNoClub() {
        final String club = "Club G";
        final String name = "Team G";
        teamDao.addTeam(name, club);
    }
}
