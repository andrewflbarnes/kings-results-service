package org.kingsski.api.config;

import org.kingsski.api.service.RaceService;
import org.kingsski.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaceConfig {

    @Autowired
    private DaoConfig daoConfig;

    @Bean
    public RaceService raceService() {
        return new RaceService(daoConfig.raceDao());
    }
}
