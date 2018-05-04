package org.kingsski.api.dao.dummy;

import org.junit.Test;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubIndividualDao;

import static org.junit.Assert.*;

public class DummyDaoFactoryTest {

    private DaoFactory factory = new DummyDaoFactory();

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("dummy", factory.getDbProfile());
    }

    @Test
    public void teamDao() throws Exception {
        assertSame(DummyTeamDao.class, factory.teamDao().getClass());
    }

    @Test
    public void raceDao() throws Exception {
        assertSame(DummyRaceDao.class, factory.raceDao().getClass());
    }

    @Test
    public void individualDao() throws Exception {
        assertSame(StubIndividualDao.class, factory.individualDao().getClass());
    }

}