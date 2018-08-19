package org.kingsski.api.query.config;

import org.kingsski.api.query.service.TeamService;
import org.kingsski.data.dao.DaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class TeamConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public TeamService teamService() {
        return new TeamService(daoFactory.teamDao());
    }
}
