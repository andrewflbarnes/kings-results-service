package org.kingsski.api.test.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.dummy.DummyDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dummy")
public class TestDummyDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig() {
        return new DummyDaoFactory();
    }
}
