package org.kingsski.api.dao.stub;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;

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
