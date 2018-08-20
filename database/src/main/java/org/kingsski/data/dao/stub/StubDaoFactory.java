package org.kingsski.data.dao.stub;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.TeamDao;

public class StubDaoFactory implements DaoFactory {

    @Override
    public String getDbProfile() {
        return "stub";
    }

    @Override
    public TeamDao teamDao() {
        return new StubTeamDao();
    }

    @Override
    public RaceDao raceDao() {
        return new StubRaceDao();
    }

    @Override
    public IndividualDao individualDao() {
        return new StubIndividualDao();
    }
}
