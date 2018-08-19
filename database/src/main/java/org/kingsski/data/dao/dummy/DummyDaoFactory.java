package org.kingsski.data.dao.dummy;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.TeamDao;

public class DummyDaoFactory implements DaoFactory {

    @Override
    public String getDbProfile() {
        return "dummy";
    }

    @Override
    public TeamDao teamDao() {
        return new DummyTeamDao();
    }

    @Override
    public RaceDao raceDao() {
        return new DummyRaceDao();
    }

    @Override
    public IndividualDao individualDao() {
        return new DummyIndividualDao();
    }
}
