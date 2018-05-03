package org.kingsski.api.config;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.service.RaceService;
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
