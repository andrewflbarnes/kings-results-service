package org.kingsski.api.dao.delegate;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.repository.RepositoryTeamDao;
import org.kingsski.api.dao.repository.TeamRepository;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("repository")
public class RepositoryDaoDelegateFactory implements DaoDelegateFactory {

    @Autowired
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
