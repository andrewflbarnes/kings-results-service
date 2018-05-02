package org.kingsski.api.config.dao;

import org.kingsski.api.config.DaoConfig;
import org.kingsski.api.config.DaoDelegateConfig;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.caching.CachingRaceDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.caching.CachingTeamDao;
import org.kingsski.api.dao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("caching")
public class CachingDaoConfig implements DaoConfig {

    @Autowired
    private DaoDelegateConfig daoDelegateConfig;

    @Override
    public TeamDao teamDao() {
        return new CachingTeamDao(daoDelegateConfig.teamDao());
    }

    @Override
    public RaceDao raceDao() {
        return new CachingRaceDao(daoDelegateConfig.raceDao());
    }

    @Override
    public IndividualDao individualDao() {
        return daoDelegateConfig.individualDao();
    }
}