package org.kingsski.api.dao.team;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.dao.repository.RepositoryTeamDao;
import org.kingsski.api.dao.repository.TeamRepository;
import org.kingsski.api.model.Team;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTeamDaoTest {

    private static final String LEAGUE = "league";
    private static final String DIVISION = "division";
    private static final String SEASON = "season";
    private static final List<Team> ALL_TEAMS = Collections.singletonList(
            new Team("ALL", "ALL", "ALL", 1, 1, 1, 1, 1)
    );
    private static final List<Team> L_TEAMS = Collections.singletonList(
            new Team("L", "L", "L", 1, 1, 1, 1, 1)
    );
    private static final List<Team> LD_TEAMS = Collections.singletonList(
            new Team("LD", "LD", "LD", 1, 1, 1, 1, 1)
    );

    @Mock
    private TeamRepository teamRepositoryMock;

    private RepositoryTeamDao repositoryTeamDao;

    @Before
    public void setUp() {
        repositoryTeamDao = new RepositoryTeamDao(teamRepositoryMock);
        when(teamRepositoryMock.findAll()).thenReturn(ALL_TEAMS);
        when(teamRepositoryMock.findByLeague(LEAGUE)).thenReturn(L_TEAMS);
        when(teamRepositoryMock.findByLeagueAndDivision(LEAGUE, DIVISION)).thenReturn(LD_TEAMS);
    }

    @Test
    public void getTeamsAll() throws Exception {
        List<Team> teams = repositoryTeamDao.getTeamsAll();

        verify(teamRepositoryMock, times(1)).findAll();
        assertEquals(ALL_TEAMS, teams);
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        List<Team> teams = repositoryTeamDao.getTeamsByLeague(LEAGUE);

        verify(teamRepositoryMock, times(1)).findByLeague(LEAGUE);
        assertEquals(L_TEAMS, teams);
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        List<Team> teams = repositoryTeamDao.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        verify(teamRepositoryMock, times(1)).findByLeagueAndDivision(LEAGUE, DIVISION);
        assertEquals(LD_TEAMS, teams);
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndDivision() throws Exception {
        List<Team> teams = repositoryTeamDao.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        verify(teamRepositoryMock, times(1)).findByLeagueAndDivision(LEAGUE, DIVISION);
        assertEquals(LD_TEAMS, teams);
    }

}