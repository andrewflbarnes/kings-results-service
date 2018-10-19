package org.kingsski.database.dao.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.jdbc.mapper.ClubMapper;
import org.kingsski.database.model.Club;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JdbcClubDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    private ClubDao clubDao;
    private List<Club> expectedClubs = new ArrayList<>();
    private Club expectedClub = new Club();

    @Before
    public void setUp() throws Exception {
        clubDao = new JdbcClubDao(jdbcTemplate);
    }

    @Test
    public void getClubs() throws Exception {
        given(jdbcTemplate.query(any(String.class), any(Object[].class), any(ClubMapper.class)))
                .willReturn(expectedClubs);

        final List<Club> actualClubs = clubDao.getClubs();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), eq(new Object[] {}), any(ClubMapper.class));

        assertNotNull(actualClubs);
        assertEquals(expectedClubs, actualClubs);
    }

    @Test
    public void getClubById() throws Exception {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(ClubMapper.class)))
                .willReturn(expectedClub);

        final Club actualClub = clubDao.getClubById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { id }), any(ClubMapper.class));

        assertNotNull(actualClub);
        assertEquals(expectedClub, actualClub);
    }

    @Test
    public void getClubByName() throws Exception {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(ClubMapper.class)))
                .willReturn(expectedClub);

        final Club actualClub = clubDao.getClubByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), eq(new Object[] { name }), any(ClubMapper.class));

        assertNotNull(actualClub);
        assertEquals(expectedClub, actualClub);
    }

}