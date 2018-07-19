package org.kingsski.api.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.model.Race;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JdbcRaceDaoTest {

    private static final String STMT_ALL = "SA";
    private static final String STMT_LEAG = "SBL";
    private static final String STMT_LERO = "SBLR";
    private static final String LEAGUE = "league";
    private static final String ROUND = "round";
    private static final String SEASON = "season";

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private List<Race> races;

    private Map<String, String> jdbcStatements = new HashMap<>();

    @InjectMocks
    private RaceDao raceDao = new JdbcRaceDao(jdbcTemplate, jdbcStatements);

    @Test
    public void getRacesAll() throws Exception {
        jdbcStatements.put(JdbcRaceDao.SELECT_ALL, STMT_ALL);
        when(jdbcTemplate.query(eq(STMT_ALL), any(Race.RaceMapper.class)))
                .thenReturn(races);

        List<Race> actual = raceDao.getRacesAll();

        assertEquals(races, actual);
    }

    @Test
    public void getRacesByLeague() throws Exception {
        jdbcStatements.put(JdbcRaceDao.SELECT_BY_LEAGUE, STMT_LEAG);
        when(jdbcTemplate.query(eq(STMT_LEAG), eq(new Object[] { LEAGUE }), any(Race.RaceMapper.class)))
                .thenReturn(races);

        List<Race> actual = raceDao.getRacesByLeague(LEAGUE);

        assertEquals(races, actual);
    }

    @Test
    public void getRacesByLeagueAndDivision() throws Exception {
        jdbcStatements.put(JdbcRaceDao.SELECT_BY_LEAGUE_ROUND, STMT_LERO);
        when(jdbcTemplate.query(eq(STMT_LERO), eq(new Object[] { LEAGUE, ROUND}), any(Race.RaceMapper.class)))
                .thenReturn(races);

        List<Race> actual = raceDao.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertEquals(races, actual);
    }

    @Test
    public void getRacesBySeasonAndLeagueAndDivision() throws Exception {
        jdbcStatements.put(JdbcRaceDao.SELECT_BY_LEAGUE_ROUND, STMT_LERO);
        when(jdbcTemplate.query(eq(STMT_LERO), eq(new Object[] { LEAGUE, ROUND}), any(Race.RaceMapper.class)))
                .thenReturn(races);

        List<Race> actual = raceDao.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertEquals(races, actual);
    }

}