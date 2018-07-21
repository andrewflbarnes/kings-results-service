package org.kingsski.api.config.dao;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.caching.CachingDaoFactory;
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
