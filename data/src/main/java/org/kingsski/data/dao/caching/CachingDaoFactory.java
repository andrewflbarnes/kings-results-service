package org.kingsski.data.dao.caching;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.TeamDao;

public class CachingDaoFactory implements DaoFactory {

    private DaoFactory daoFactory;

    public CachingDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public String getDbProfile() {
        return daoFactory.getDbProfile();
    }

    @Override
    public TeamDao teamDao() {
        return new CachingTeamDao(daoFactory.teamDao());
    }

    @Override
    public RaceDao raceDao() {
        return new CachingRaceDao(daoFactory.raceDao());
    }

    @Override
    public IndividualDao individualDao() {
        return daoFactory.individualDao();
    }
}
