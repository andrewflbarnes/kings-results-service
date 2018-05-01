package org.kingsski.api.config;

import org.kingsski.api.service.IndividualService;
import org.kingsski.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfig {

    @Autowired
    private DaoConfig daoConfig;

    @Bean
    public TeamService teamService() {
        return new TeamService(daoConfig.teamDao());
    }
}
