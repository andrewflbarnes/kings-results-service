package org.kingsski.api.config.dao.delegate;

import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.delegate.DummyDaoDelegateFactory;
import org.kingsski.api.dao.delegate.JdbcDaoDelegateFactory;
import org.kingsski.api.dao.jdbc.JdbcTeamDao;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("jdbc")
public class JdbcDaoDelegateConfig {

    @Bean("delegate")
    public DaoDelegateFactory daoDelegateConfig() {
        return new JdbcDaoDelegateFactory();
    }
}
