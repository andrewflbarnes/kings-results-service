package org.kingsski.kaas.database.club.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.TestUtil;
import org.kingsski.kaas.database.club.Club;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.exception.EntityAlreadyExistsException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class JdbcClubDaoTest {

    @Mock
    private NamedParameterJdbcTemplate jdbcTemplate;
    private ClubDao clubDao;
    private List<Club> expectedClubs = new ArrayList<>();
    private Club expectedClub = new Club();

    @Before
    public void setUp() {
        clubDao = new JdbcClubDao(jdbcTemplate);
    }

    @Test
    public void getClubs() {
        given(jdbcTemplate.query(any(String.class), any(ClubMapper.class)))
                .willReturn(expectedClubs);

        final List<Club> actualClubs = clubDao.getClubs();

        then(jdbcTemplate).should(times(1))
                .query(any(String.class), any(ClubMapper.class));

        assertNotNull(actualClubs);
        assertEquals(expectedClubs, actualClubs);
    }

    @Test
    public void getClubById() {
        final long id = 1;
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(ClubMapper.class)))
                .willReturn(expectedClub);

        final Club actualClub = clubDao.getClubById(id);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(ClubMapper.class));

        assertNotNull(actualClub);
        assertEquals(expectedClub, actualClub);
    }

    @Test
    public void getClubByName() {
        final String name = "boom";
        given(jdbcTemplate.queryForObject(any(String.class), any(SqlParameterSource.class), any(ClubMapper.class)))
                .willReturn(expectedClub);

        final Club actualClub = clubDao.getClubByName(name);

        then(jdbcTemplate).should(times(1))
                .queryForObject(any(String.class), any(SqlParameterSource.class), any(ClubMapper.class));

        assertNotNull(actualClub);
        assertEquals(expectedClub, actualClub);
    }

    @Test
    public void addClub() {
        final String name = "boom";
        final long id = 9876L;
        given(jdbcTemplate.update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class)))
                .willAnswer(generatedKeyAnswer(id));

        final Club actualClub = clubDao.addClub(name);

        then(jdbcTemplate).should(times(1))
                .update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class));

        assertNotNull(actualClub);
        assertEquals(name, actualClub.getName());
        assertEquals(id, actualClub.getId());
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void addExistingClub() {
        final String name = "boom2";
        final long id = 9876L;
        given(jdbcTemplate.update(any(String.class), any(SqlParameterSource.class), any(KeyHolder.class)))
                .willAnswer(generatedKeyAnswer(id))
                .willThrow(new DuplicateKeyException(""));

        clubDao.addClub(name);
        clubDao.addClub(name);
    }

    private Answer<?> generatedKeyAnswer(long id) {
        return TestUtil.generatedKeyAnswer("club_id", id);
    }
}
