package org.kingsski.kaas.service.club;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceTest {

    @Mock
    private ClubDao clubDao;

    public ClubService clubService;

    @Before
    public void setUp() {
        this.clubService = new ClubService(clubDao);
    }

    @Test
    public void getClubs() {
        final List<Club> clubs = new ArrayList<>();
        given(clubDao.getClubs())
                .willReturn(clubs);

        List<Club> returnedClubs = clubService.getClubs();

        assertEquals(clubs, returnedClubs);
        then(clubDao).should(times(1)).getClubs();
    }

    @Test
    public void getClubByName() {
        final String name = "name";
        final Club club = new Club();
        given(clubDao.getClubByName(name))
                .willReturn(club);

        Club returnedClub = clubService.getClubByName(name);

        assertEquals(club, returnedClub);
        then(clubDao).should(times(1)).getClubByName(name);
    }

    @Test
    public void getClubById() {
        final long id = 99L;
        final Club club = new Club();
        given(clubDao.getClubById(id))
                .willReturn(club);

        Club returnedClub = clubService.getClubById(id);

        then(clubDao).should(times(1)).getClubById(id);
        assertEquals(club, returnedClub);
    }


    @Test
    public void addClub() throws Exception {
        final String name = "sjklgnl";
        final Club club = Club.builder().name(name).build();
        given(clubDao.getClubByName(name))
                .willReturn(null);
        given(clubDao.addClub(name))
                .willReturn(club);

        Club returnedClub = clubService.addClub(name);

        then(clubDao).should(times(1)).getClubByName(name);
        then(clubDao).should(times(1)).addClub(name);
        assertEquals(club, returnedClub);
    }


    @Test(expected = EntityAlreadyExistsException.class)
    public void addExistingClub() throws Exception {
        final String name = "dfagsrtghrts";

        given(clubDao.getClubByName(name))
                .willReturn(new Club());

        clubService.addClub(name);
    }
}
