package org.kingsski.data.dao.caching;

import org.kingsski.data.dao.DaoFactory;

public class CachingDaoFactory implements DaoFactory {

    private DaoFactory daoFactory;

    public CachingDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public String getDbProfile() {
        return daoFactory.getDbProfile();
    }
}
