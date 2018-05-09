package org.kingsski.api.dao.caching;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.config.TestJdbcConfig;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.jdbc.JdbcIndividualDao;
import org.kingsski.api.dao.jdbc.JdbcTeamDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJdbcConfig.class })
@ActiveProfiles({ "jdbc" })
public class JdbcDaoFactoryTest {

    @Resource
    private DaoFactory daoFactory;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void dbProfile() {
        assertEquals("jdbc", daoFactory.getDbProfile());
    }

    @Test
    public void teamDao() {
        TeamDao teamDao = daoFactory.teamDao();

        assertEquals(JdbcTeamDao.class, teamDao.getClass());
        assertEquals(jdbcTemplate.query(JdbcTeamDao.SELECT_ALL, (RowMapper)null),
                teamDao.getTeamsAll());
        assertEquals(jdbcTemplate.query(JdbcTeamDao.SELECT_BY_LEAGUE, (RowMapper)null),
                teamDao.getTeamsByLeague(""));
        assertEquals(jdbcTemplate.query(JdbcTeamDao.SELECT_BY_LEAGUE_DIVISION, (RowMapper)null),
                teamDao.getTeamsByLeagueAndDivision("", ""));
    }

    @Test
    public void raceDao() {
        RaceDao raceDao = daoFactory.raceDao();

        assertEquals(StubRaceDao.class, raceDao.getClass());
    }

    @Test
    public void individualDao() {
        IndividualDao individualDao = daoFactory.individualDao();

        assertEquals(JdbcIndividualDao.class, individualDao.getClass());
        assertEquals(jdbcTemplate.query(JdbcIndividualDao.SELECT_ALL, (RowMapper)null),
                individualDao.getAllIndividuals());
        assertEquals(jdbcTemplate.query(JdbcIndividualDao.SELECT_BY_CLUB, (RowMapper)null),
                individualDao.getIndividualsByClub(""));
        assertEquals(jdbcTemplate.query(JdbcIndividualDao.SELECT_BY_DISCIPLINE, (RowMapper)null),
                individualDao.getIndividualsByDiscipline(""));
    }
}