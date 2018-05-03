package org.kingsski.api.config;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfig {

    @Autowired
    private DaoFactory daoFactory;

    @Bean
    public TeamService teamService() {
        return new TeamService(daoFactory.teamDao());
    }
}
