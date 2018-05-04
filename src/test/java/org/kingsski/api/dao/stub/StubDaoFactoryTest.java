package org.kingsski.api.dao.stub;

import org.junit.Test;
import org.kingsski.api.dao.DaoFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class StubDaoFactoryTest {

    private DaoFactory factory = new StubDaoFactory();

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("stub", factory.getDbProfile());
    }

    @Test
    public void teamDao() throws Exception {
        assertSame(StubTeamDao.class, factory.teamDao().getClass());
    }

    @Test
    public void raceDao() throws Exception {
        assertSame(StubRaceDao.class, factory.raceDao().getClass());
    }

    @Test
    public void individualDao() throws Exception {
        assertSame(StubIndividualDao.class, factory.individualDao().getClass());
    }

}