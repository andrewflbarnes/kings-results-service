package org.kingsski.data.dao.caching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.TeamDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CachingDaoFactoryTest {

    @Mock
    private DaoFactory delegateFactory;
    @Mock
    private TeamDao mockTeamDao;
    @Mock
    private RaceDao mockRaceDao;
    @Mock
    private IndividualDao mockIndividualDao;
    @InjectMocks
    private DaoFactory factory = new CachingDaoFactory(delegateFactory);

    @Before
    public void setUp() {
        when(delegateFactory.getDbProfile()).thenReturn("delegate");
        when(delegateFactory.teamDao()).thenReturn(mockTeamDao);
        when(delegateFactory.raceDao()).thenReturn(mockRaceDao);
        when(delegateFactory.individualDao()).thenReturn(mockIndividualDao);
    }

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("delegate", factory.getDbProfile());
    }

    @Test
    public void teamDao() throws Exception {
        TeamDao teamDao = factory.teamDao();

        teamDao.getTeamsAll();
        teamDao.getTeamsByLeague("");
        teamDao.getTeamsByLeagueAndDivision("", "");
        teamDao.getTeamsBySeasonAndLeagueAndDivision("", "", "");

        assertEquals(CachingTeamDao.class, teamDao.getClass());
        verify(mockTeamDao).getTeamsByLeague("");
        verify(mockTeamDao).getTeamsByLeagueAndDivision("", "");
        verify(mockTeamDao).getTeamsBySeasonAndLeagueAndDivision("", "", "");
    }

    @Test
    public void raceDao() throws Exception {
        RaceDao raceDao = factory.raceDao();

        raceDao.getRacesAll();
        raceDao.getRacesByLeague("");
        raceDao.getRacesByLeagueAndRound("", "");
        raceDao.getRacesBySeasonAndLeagueAndRound("", "", "");

        assertEquals(CachingRaceDao.class, raceDao.getClass());
        verify(mockRaceDao).getRacesAll();
        verify(mockRaceDao).getRacesByLeague("");
        verify(mockRaceDao).getRacesByLeagueAndRound("", "");
        verify(mockRaceDao).getRacesBySeasonAndLeagueAndRound("", "", "");
    }

    @Test
    public void individualDao() throws Exception {
        assertEquals(mockIndividualDao, factory.individualDao());
    }

}