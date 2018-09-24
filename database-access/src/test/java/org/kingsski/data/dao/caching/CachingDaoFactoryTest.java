package org.kingsski.data.dao.caching;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.DaoFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CachingDaoFactoryTest {

    @Mock
    private DaoFactory delegateFactory;
    @InjectMocks
    private DaoFactory factory = new CachingDaoFactory(delegateFactory);

    @Before
    public void setUp() {
        when(delegateFactory.getDbProfile()).thenReturn("delegate");
    }

    @Test
    public void getDbProfile() throws Exception {
        assertEquals("delegate", factory.getDbProfile());
    }

}