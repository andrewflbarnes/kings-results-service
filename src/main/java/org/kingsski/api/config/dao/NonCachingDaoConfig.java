package org.kingsski.api.config.dao;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.delegate.DaoDelegateFactory;
import org.kingsski.api.dao.caching.NonCachingDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("nocaching")
public class NonCachingDaoConfig {

//    @Autowired
//    private DaoDelegateFactory daoDelegateFactory;
//
//    @Bean
//    public DaoFactory daoDelegateConfig() {
//        return new NonCachingDaoFactory(daoDelegateFactory);
//    }
}
