package org.kingsski.api.dao.repository;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;

import javax.annotation.Resource;

public class RepositoryDaoFactory implements DaoFactory {

    @Resource
    private TeamRepository teamRepository;

    @Override
    public String getDbProfile() {
        return "repository";
    }

    @Override
    public TeamDao teamDao() {
        return new RepositoryTeamDao(teamRepository);
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
