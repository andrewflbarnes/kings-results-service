package org.kingsski.kaas.service.competition;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CompetitionConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public CompetitionDao competitionDao() {
        return daoFactory.newCompetitionDao();
    }
}
