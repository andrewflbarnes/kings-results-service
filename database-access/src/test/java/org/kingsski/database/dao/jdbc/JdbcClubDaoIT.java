package org.kingsski.database.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.model.Club;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class JdbcClubDaoIT extends AbstractDaoIT {

    private ClubDao clubDao;

    @Before
    public void setUp() {
        clubDao = new JdbcClubDao(jdbcTemplate);
        clearDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    @Test
    public void getClubs() {
        List<String> clubNames = new ArrayList<>();
        clubNames.add("Club A");
        clubNames.add("Club B");

        for (String club : clubNames) {
            addClubAndTeams(club, 0);
        }

        List<Club> clubs = clubDao.getClubs();

        assertNotNull(clubs);
        assertEquals(2, clubs.size());
        assertTrue(clubNames.contains(clubs.get(0).getName()));
        assertTrue(clubNames.contains(clubs.get(1).getName()));
    }

    @Test
    public void getClubById() {
        addClubAndTeams("Club C", 3);

        List<Club> clubs = clubDao.getClubs();

        assertNotNull(clubs);
        assertEquals(1, clubs.size());

        Club club = clubDao.getClubById(clubs.get(0).getId());

        assertNotNull(club);
        assertEquals(clubs.get(0), club);
    }

    @Test
    public void getClubByIdNotExist() {
        Club club = clubDao.getClubById(9999);

        assertNull(club);
    }

    @Test
    public void getClubByName() {
        final String clubName = "Club D";
        addClubAndTeams(clubName, 4);

        Club club = clubDao.getClubByName(clubName);

        assertNotNull(club);
        assertEquals(club.getName(), clubName);
    }

    @Test
    public void getClubByNameNotExist() {
        Club club = clubDao.getClubByName("Club does not exist");

        assertNull(club);
    }
}
