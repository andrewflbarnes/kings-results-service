package org.kingsski.data.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.TeamDao;
import org.kingsski.data.model.Team;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:jdbc-config.xml")
public class JdbcTeamDaoIT extends AbstractDaoIT {

    private static final String DELETE_ALL = "DELETE FROM kings_teams";
    private static final int ID = 1;
    private static final String SEASON = "2015/2016";
    private static final String DIVISION = "MIXED";
    private static final String LEAGUE = "Northern";
    private static final int POSITION = 2;
    private static final String TEAM = "SKUM 1";

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcStatements;

    private TeamDao teamDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        teamDao = new JdbcTeamDao(jdbcTemplate, jdbcStatements);
        jdbcTemplate.execute(DELETE_ALL);
    }

    @After
    public void tearDown() {
        jdbcTemplate.execute(DELETE_ALL);
    }

    @Test
    public void getAllTeams() throws Exception {
        Team expectedTeam = addDefaultTeam();

        List<Team> teams = teamDao.getTeamsAll();

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertTeamEquals(expectedTeam, teams.get(0));
    }

    @Test
    public void getTeamsByLeague() throws Exception {
        Team expectedTeam = addDefaultTeam();

        List<Team> teams = teamDao.getTeamsByLeague(LEAGUE);

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertTeamEquals(expectedTeam, teams.get(0));

        teams = teamDao.getTeamsByLeague("NOT A LEAGUE");

        assertNotNull(teams);
        assertEquals(0, teams.size());
    }

    @Test
    public void getTeamsByLeagueAndDivision() throws Exception {
        Team expectedTeam = addDefaultTeam();

        List<Team> teams = teamDao.getTeamsByLeagueAndDivision(LEAGUE, DIVISION);

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertTeamEquals(expectedTeam, teams.get(0));

        teams = teamDao.getTeamsByLeagueAndDivision("NOT A LEAGUE", DIVISION);

        assertNotNull(teams);
        assertEquals(0, teams.size());

        teams = teamDao.getTeamsByLeagueAndDivision(LEAGUE, "NOT A DIVISION");

        assertNotNull(teams);
        assertEquals(0, teams.size());
    }

    @Test
    public void getTeamsBySeasonAndLeagueAndRound() throws Exception {
        Team expectedTeam = addDefaultTeam();

        List<Team> teams = teamDao.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, DIVISION);

        assertNotNull(teams);
        assertEquals(1, teams.size());
        assertTeamEquals(expectedTeam, teams.get(0));

        teams = teamDao.getTeamsBySeasonAndLeagueAndDivision(SEASON, "NOT A LEAGUE", DIVISION);

        assertNotNull(teams);
        assertEquals(0, teams.size());

        teams = teamDao.getTeamsBySeasonAndLeagueAndDivision(SEASON, LEAGUE, "NOT A DIVISION");

        assertNotNull(teams);
        assertEquals(0, teams.size());

        // TODO - when functionality is added
//        teams = teamDao.getTeamsBySeasonAndLeagueAndDivision(("NOT A SEASON", LEAGUE, DIVISION);
//
//        assertNotNull(teams);
//        assertEquals(0, teams.size());
    }

    private Team addDefaultTeam() {
        Team team = new Team();
        team.setId(ID);
        team.setDivision(DIVISION);
        team.setLeague(LEAGUE);
        team.setPosition(POSITION);
        team.setTeamName(TEAM);
        team.setR1(3);
        team.setR2(1);
        team.setR3(4);
        team.setR4(2);
        team.setTotal(10);

        addTeam(team);

        return team;
    }

    private void addTeam(Team team) {
        jdbcTemplate.update(
                "INSERT INTO kings_teams(" +
                        "id, division, league, position, name, r1, r2, r3, r4, total) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                team.getId(),
                team.getDivision(),
                team.getLeague(),
                team.getPosition(),
                team.getTeamName(),
                team.getR1(),
                team.getR2(),
                team.getR3(),
                team.getR4(),
                team.getTotal());
    }

    private void assertTeamEquals(Team expected, Team actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDivision(), actual.getDivision());
        assertEquals(expected.getLeague(), actual.getLeague());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getTeamName(), actual.getTeamName());
        assertEquals(expected.getR1(), actual.getR1());
        assertEquals(expected.getR2(), actual.getR2());
        assertEquals(expected.getR3(), actual.getR3());
        assertEquals(expected.getR4(), actual.getR4());
        assertEquals(expected.getTotal(), actual.getTotal());
        assertEquals(expected.getOrderedScore1(), actual.getOrderedScore1());
        assertEquals(expected.getOrderedScore2(), actual.getOrderedScore2());
        assertEquals(expected.getOrderedScore3(), actual.getOrderedScore3());
        assertEquals(expected.getOrderedScore4(), actual.getOrderedScore4());
    }
}