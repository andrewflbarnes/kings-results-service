package org.kingsski.api.dao.jdbc;

import org.junit.Test;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class JdbcDaoFactoryTest {

    private DaoFactory factory = new JdbcDaoFactory(null, null);

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("jdbc", factory.getDbProfile());
    }

    @Test
    public void teamDao() throws Exception {
        assertSame(JdbcTeamDao.class, factory.teamDao().getClass());
    }

    @Test
    public void raceDao() throws Exception {
        assertSame(StubRaceDao.class, factory.raceDao().getClass());
    }

    @Test
    public void individualDao() throws Exception {
        assertSame(JdbcIndividualDao.class, factory.individualDao().getClass());
    }

}