package org.kingsski.api.config.dao;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.dummy.DummyDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dummy")
public class DummyDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig() {
        return new DummyDaoFactory();
    }
}
