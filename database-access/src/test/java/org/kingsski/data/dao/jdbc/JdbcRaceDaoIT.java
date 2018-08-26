package org.kingsski.data.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.model.Race;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc-config.xml")
public class JdbcRaceDaoIT extends AbstractDaoIT {

    private static final String DELETE_ALL = "DELETE FROM kings_races";
    private static final int ID = 1;
    private static final String LEAGUE = "Northern";
    private static final String ROUND = "1";
    private static final String DIVISION = "MIXED";
    private static final String SET = "1";
    private static final boolean NEXT = true;
    private static final String TEAM_ONE = "SKUM 1";
    private static final String TEAM_TWO = "Leeds 1";
    private static final String SEASON = "2015/2016";

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcStatements;

    private RaceDao raceDao;

    @Before
    public void setUp() {
        super.setUp();
        raceDao = new JdbcRaceDao(jdbcTemplate, jdbcStatements);
        jdbcTemplate.execute(DELETE_ALL);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute(DELETE_ALL);
    }

    @Test
    public void getAllRaces() throws Exception {
        Race expectedRace = addDefaultRace();

        List<Race> races = raceDao.getRacesAll();

        assertNotNull(races);
        assertEquals(1, races.size());
        assertRaceEquals(expectedRace, races.get(0));
    }

    @Test
    public void getRacesByClub() throws Exception {
        Race expectedRace = addDefaultRace();

        List<Race> races = raceDao.getRacesByLeague(LEAGUE);

        assertNotNull(races);
        assertEquals(1, races.size());
        assertRaceEquals(expectedRace, races.get(0));

        races = raceDao.getRacesByLeague("NOT A LEAGUE");

        assertNotNull(races);
        assertEquals(0, races.size());
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        Race expectedRace = addDefaultRace();

        List<Race> races = raceDao.getRacesByLeagueAndRound(LEAGUE, ROUND);

        assertNotNull(races);
        assertEquals(1, races.size());
        assertRaceEquals(expectedRace, races.get(0));

        races = raceDao.getRacesByLeagueAndRound("NOT A LEAGUE", ROUND);

        assertNotNull(races);
        assertEquals(0, races.size());

        races = raceDao.getRacesByLeagueAndRound(LEAGUE, "NOT A ROUND");

        assertNotNull(races);
        assertEquals(0, races.size());
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        Race expectedRace = addDefaultRace();

        List<Race> races = raceDao.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, ROUND);

        assertNotNull(races);
        assertEquals(1, races.size());
        assertRaceEquals(expectedRace, races.get(0));

        races = raceDao.getRacesBySeasonAndLeagueAndRound(SEASON, "NOT A LEAGUE", ROUND);

        assertNotNull(races);
        assertEquals(0, races.size());

        races = raceDao.getRacesBySeasonAndLeagueAndRound(SEASON, LEAGUE, "NOT A ROUND");

        assertNotNull(races);
        assertEquals(0, races.size());

        // TODO - when functionality is added
//        races = raceDao.getRacesBySeasonAndLeagueAndRound("NOT A SEASON", LEAGUE, ROUND);
//
//        assertNotNull(races);
//        assertEquals(0, races.size());
    }

    private Race addDefaultRace() {
        Race race = new Race();
        race.setId(ID);
        race.setLeague(LEAGUE);
        race.setDivision(DIVISION);
        race.setRound(ROUND);
        race.setSet(SET);
        race.setRaceNo(ID);
        race.setTeamOne(TEAM_ONE);
        race.setTeamTwo(TEAM_TWO);
        race.setWinner(TEAM_ONE);
        race.setNext(NEXT);
        race.setTeamOneDsq(TEAM_ONE);
        race.setTeamTwoDsq(TEAM_TWO);

        addRace(race);

        return race;
    }

    private void addRace(Race race) {
        jdbcTemplate.update(
                "INSERT INTO kings_races(" +
                        "id, league, round, `set`, race_no, division, team_one, team_two, " +
                        "winner, team_one_dsq, team_two_dsq, next) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                race.getId(),
                race.getLeague(),
                race.getRound(),
                race.getSet(),
                race.getRaceNo(),
                race.getDivision(),
                race.getTeamOne(),
                race.getTeamTwo(),
                race.getWinner(),
                race.getTeamOneDsq(),
                race.getTeamTwoDsq(),
                race.isNext() ? 1 : 0);
    }

    private void assertRaceEquals(Race expected, Race actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getLeague(), actual.getLeague());
        assertEquals(expected.getRound(), actual.getRound());
        assertEquals(expected.getSet(), actual.getSet());
        assertEquals(expected.getRaceNo(), actual.getRaceNo());
        assertEquals(expected.getDivision(), actual.getDivision());
        assertEquals(expected.getTeamOne(), actual.getTeamOne());
        assertEquals(expected.getTeamTwo(), actual.getTeamTwo());
        assertEquals(expected.getWinner(), actual.getWinner());
        assertEquals(expected.getTeamOneDsq(), actual.getTeamOneDsq());
        assertEquals(expected.getTeamTwoDsq(), actual.getTeamTwoDsq());
        assertEquals(expected.isNext(), actual.isNext());
    }
}