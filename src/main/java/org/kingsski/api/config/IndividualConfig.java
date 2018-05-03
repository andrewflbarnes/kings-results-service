package org.kingsski.api.config;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.service.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndividualConfig {

    @Autowired
    private DaoFactory daoFactory;

    @Bean
    public IndividualService individualService() {
        return new IndividualService(daoFactory.individualDao());
    }
}
