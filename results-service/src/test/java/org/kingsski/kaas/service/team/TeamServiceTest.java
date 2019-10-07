package org.kingsski.kaas.service.team;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.team.Team;
import org.kingsski.kaas.database.team.TeamDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.kingsski.kaas.service.exception.EntityMissingParentException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamDao teamDao;

    @Mock
    private ClubDao clubDao;

    public TeamService teamService;

    @Before
    public void setUp() {
        this.teamService = new TeamService(teamDao, clubDao);
    }

    @Test
    public void getTeams() {
        final List<Team> teams = new ArrayList<>();
        given(teamDao.getTeams())
                .willReturn(teams);

        List<Team> returnedTeams = teamService.getTeams();

        assertEquals(teams, returnedTeams);
        then(teamDao).should(times(1)).getTeams();
    }

    @Test
    public void getTeamByName() {
        final String name = "name";
        final Team team = new Team();
        given(teamDao.getTeamByName(name))
                .willReturn(team);

        Team returnedTeam = teamService.getTeamByName(name);

        assertEquals(team, returnedTeam);
        then(teamDao).should(times(1)).getTeamByName(name);
    }

    @Test
    public void getTeamById() {
        final long id = 99L;
        final Team team = new Team();
        given(teamDao.getTeamById(id))
                .willReturn(team);

        Team returnedTeam = teamService.getTeamById(id);

        assertEquals(team, returnedTeam);
        then(teamDao).should(times(1)).getTeamById(id);
    }

    @Test
    public void addTeam() throws Exception {
        final String name = "team";
        final String clubName = "club";
        final Team team = Team.builder().name(name).build();
        final Club club = Club.builder().name(clubName).build();

        given(clubDao.getClubByName(clubName))
                .willReturn(club);
        given(teamDao.getTeamByName(name))
                .willReturn(null);
        given(teamDao.addTeam(name, clubName))
                .willReturn(team);

        Team returnedTeam = teamService.addTeam(name, clubName);

        then(clubDao).should(times(1)).getClubByName(clubName);
        then(teamDao).should(times(1)).getTeamByName(name);
        then(teamDao).should(times(1)).addTeam(name, clubName);
        assertEquals(team, returnedTeam);
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void addExistingTeam() throws Exception {
        final String name = "team";
        final String clubName = "club";
        final Team team = Team.builder().name(name).build();
        final Club club = Club.builder().name(clubName).build();

        given(clubDao.getClubByName(clubName))
                .willReturn(club);
        given(teamDao.getTeamByName(name))
                .willReturn(team);

        teamService.addTeam(name, clubName);
    }


    @Test(expected = EntityMissingParentException.class)
    public void addTeamNoClub() throws Exception {
        final String name = "team";
        final String clubName = "club";

        given(clubDao.getClubByName(clubName))
                .willReturn(null);

        teamService.addTeam(name, clubName);
    }
}
