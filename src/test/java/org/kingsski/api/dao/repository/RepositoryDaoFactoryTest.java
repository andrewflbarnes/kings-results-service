package org.kingsski.api.dao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.config.TestJdbcConfig;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestJdbcConfig.class})
//@ActiveProfiles({ "repository" })
public class RepositoryDaoFactoryTest {

//    @Resource
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