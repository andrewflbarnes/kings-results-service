package org.kingsski.api.query.config;

import org.kingsski.database.dao.DaoFactory;
import org.kingsski.database.dao.caching.CachingDaoFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.Resource;

@Configuration
@Profile("caching")
public class CachingDaoConfig {

    @Resource
    @Qualifier("delegate")
    private DaoFactory daoFactory;

    @Bean
    @Primary
    public DaoFactory daoDelegateConfig() {
        return new CachingDaoFactory(daoFactory);
    }
}
