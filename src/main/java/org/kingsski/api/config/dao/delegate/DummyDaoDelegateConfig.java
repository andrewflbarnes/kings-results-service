package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.config.DaoDelegateConfig;
import org.kingsski.api.dao.individual.IndividualDao;
import org.kingsski.api.dao.individual.StubIndividualDao;
import org.kingsski.api.dao.race.DummyRaceDao;
import org.kingsski.api.dao.race.RaceDao;
import org.kingsski.api.dao.race.StubRaceDao;
import org.kingsski.api.dao.team.DummyTeamDao;
import org.kingsski.api.dao.team.StubTeamDao;
import org.kingsski.api.dao.team.TeamDao;
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
