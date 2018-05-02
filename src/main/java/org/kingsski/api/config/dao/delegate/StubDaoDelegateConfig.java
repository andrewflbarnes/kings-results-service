package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.config.DaoDelegateConfig;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.kingsski.api.dao.stub.StubTeamDao;
import org.kingsski.api.dao.TeamDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stub")
public class StubDaoDelegateConfig implements DaoDelegateConfig {

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
