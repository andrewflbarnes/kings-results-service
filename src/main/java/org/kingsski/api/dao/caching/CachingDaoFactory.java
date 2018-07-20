package org.kingsski.api.dao.caching;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;

public class CachingDaoFactory implements org.kingsski.api.dao.DaoFactory {

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
