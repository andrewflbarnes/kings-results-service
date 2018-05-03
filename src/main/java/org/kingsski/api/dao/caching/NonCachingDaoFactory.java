package org.kingsski.api.dao.caching;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.delegate.DaoDelegateFactory;

public class NonCachingDaoFactory implements DaoFactory {

    private DaoDelegateFactory daoDelegateFactory;

    public NonCachingDaoFactory(DaoDelegateFactory daoDelegateFactory) {
        this.daoDelegateFactory = daoDelegateFactory;
    }

    @Override
    public String getDbProfile() {
        return daoDelegateFactory.getDbProfile();
    }

    @Override
    public TeamDao teamDao() {
        return daoDelegateFactory.teamDao();
    }

    @Override
    public RaceDao raceDao() {
        return daoDelegateFactory.raceDao();
    }

    @Override
    public IndividualDao individualDao() {
        return daoDelegateFactory.individualDao();
    }
}
