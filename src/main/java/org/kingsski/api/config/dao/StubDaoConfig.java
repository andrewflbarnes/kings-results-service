package org.kingsski.api.config.dao;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.stub.StubDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stub")
public class StubDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig() {
        return new StubDaoFactory();
    }
}
