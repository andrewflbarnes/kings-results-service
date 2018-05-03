package org.kingsski.api.dao.caching;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.caching.CachingRaceDao;
import org.kingsski.api.dao.caching.CachingTeamDao;

public class CachingDaoFactory implements DaoFactory {

    private DaoDelegateFactory daoDelegateFactory;

    public CachingDaoFactory(DaoDelegateFactory daoDelegateFactory) {
        this.daoDelegateFactory = daoDelegateFactory;
    }

    @Override
    public String getDbProfile() {
        return daoDelegateFactory.getDbProfile();
    }

    @Override
    public TeamDao teamDao() {
        return new CachingTeamDao(daoDelegateFactory.teamDao());
    }

    @Override
    public RaceDao raceDao() {
        return new CachingRaceDao(daoDelegateFactory.raceDao());
    }

    @Override
    public IndividualDao individualDao() {
        return daoDelegateFactory.individualDao();
    }
}
