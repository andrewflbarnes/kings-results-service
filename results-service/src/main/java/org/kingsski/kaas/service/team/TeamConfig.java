package org.kingsski.kaas.service.team;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class TeamConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public TeamDao teamDao() {
        return daoFactory.newTeamDao();
    }
}
