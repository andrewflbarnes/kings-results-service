package org.kingsski.api.query.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.api.query.service.RaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RaceConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public RaceService raceService() {
        return new RaceService(daoFactory.raceDao());
    }
}
