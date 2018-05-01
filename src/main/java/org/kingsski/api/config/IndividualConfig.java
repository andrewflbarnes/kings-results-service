package org.kingsski.api.config;

import org.kingsski.api.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndividualConfig {

    @Autowired
    private DaoConfig daoConfig;

    @Bean
    public IndividualService individualService() {
        return new IndividualService(daoConfig.individualDao());
    }
}
