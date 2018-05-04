package org.kingsski.api.dao.repository;

import org.junit.Test;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubDaoFactory;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.kingsski.api.dao.stub.StubTeamDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class RepositoryDaoFactoryTest {

    private DaoFactory factory = new RepositoryDaoFactory();

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("repository", factory.getDbProfile());
    }

    @Test
    public void teamDao() throws Exception {
        assertSame(RepositoryTeamDao.class, factory.teamDao().getClass());
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