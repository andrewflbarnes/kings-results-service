package org.kingsski.data.dao.dummy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.DaoFactory;
import org.kingsski.api.test.config.TestDummyDaoConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDummyDaoConfig.class })
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