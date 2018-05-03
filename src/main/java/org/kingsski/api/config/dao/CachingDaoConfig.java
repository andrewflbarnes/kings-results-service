package org.kingsski.api.config.dao;

import org.kingsski.api.dao.caching.CachingDaoFactory;
import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("caching")
public class CachingDaoConfig {

    @Autowired
    @Qualifier("delegate")
    private DaoDelegateFactory daoDelegateFactory;

    @Bean
    @Primary
    public DaoFactory daoDelegateConfig() {
        return new CachingDaoFactory(daoDelegateFactory);
    }
}
