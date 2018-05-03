package org.kingsski.api.config;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaceConfig {

    @Autowired
    private DaoFactory daoFactory;

    @Bean
    public RaceService raceService() {
        return new RaceService(daoFactory.raceDao());
    }
}
