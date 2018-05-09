package org.kingsski.api.dao.dummy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.config.dao.DummyDaoConfig;
import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DummyDaoConfig.class })
@ActiveProfiles({ "dummy" })
public class DummyDaoFactoryTest {

    @Resource
    private DaoFactory factory;

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
        assertSame(DummyIndividualDao.class, factory.individualDao().getClass());
    }

}