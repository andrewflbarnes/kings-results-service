package org.kingsski.data.dao.stub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.DaoFactory;
import org.kingsski.api.test.config.TestStubDaoConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestStubDaoConfig.class })
@ActiveProfiles({ "stub" })
public class StubDaoFactoryTest {

    @Resource
    private DaoFactory factory;

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