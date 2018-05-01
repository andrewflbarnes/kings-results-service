package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.config.DaoDelegateConfig;
import org.kingsski.api.dao.individual.IndividualDao;
import org.kingsski.api.dao.individual.StubIndividualDao;
import org.kingsski.api.dao.race.RaceDao;
import org.kingsski.api.dao.race.StubRaceDao;
import org.kingsski.api.dao.team.RepositoryTeamDao;
import org.kingsski.api.dao.team.TeamDao;
import org.kingsski.api.dao.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("repository")
public class RepositoryDaoDelegateConfig implements DaoDelegateConfig {

    @Autowired
    private TeamRepository teamRepository;

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
