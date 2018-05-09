package org.kingsski.api.dao.dummy;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.stub.StubIndividualDao;

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
