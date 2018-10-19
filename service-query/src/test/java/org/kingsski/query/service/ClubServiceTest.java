package org.kingsski.query.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.model.Club;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class ClubServiceTest {

    @TestConfiguration
    static class ClubServiceTestConfiguration {
        @Bean
        public ClubService clubService() {
            return new ClubService();
        }
    }

    @MockBean
    private ClubDao clubDao;

    @Resource
    private ClubService clubService;

    @Test
    public void getClubs() {
        final List<Club> clubs = new ArrayList<>();
        given(clubDao.getClubs()).willReturn(clubs);

        List<Club> returnedClubs = clubService.getClubs();

        assertEquals(clubs, returnedClubs);
        then(clubDao).should(times(1)).getClubs();
    }

    @Test
    public void getClubByName() {
        final String name = "name";
        final Club club = new Club();
        given(clubDao.getClubByName(name)).willReturn(club);

        Club returnedClub = clubService.getClubByName(name);

        assertEquals(club, returnedClub);
        then(clubDao).should(times(1)).getClubByName(name);
    }

    @Test
    public void getClubById() {
        final long id = 99L;
        final Club club = new Club();
        given(clubDao.getClubById(id)).willReturn(club);

        Club returnedClub = clubService.getClubById(id);

        assertEquals(club, returnedClub);
        then(clubDao).should(times(1)).getClubById(id);
    }
}