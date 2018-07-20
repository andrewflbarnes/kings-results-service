package org.kingsski.api.config.dao;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.repository.RepositoryDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("repository")
public class RepositoryDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig() {
        return new RepositoryDaoFactory();
    }
}
