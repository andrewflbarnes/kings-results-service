package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.config.DaoDelegateConfig;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.dummy.DummyRaceDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.dummy.DummyTeamDao;
import org.kingsski.api.dao.TeamDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dummy")
public class DummyDaoDelegateConfig implements DaoDelegateConfig {

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
        return new StubIndividualDao();
    }
}
