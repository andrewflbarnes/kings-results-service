package org.kingsski.api.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.api.service.IndividualService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class IndividualConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public IndividualService individualService() {
        return new IndividualService(daoFactory.individualDao());
    }
}
