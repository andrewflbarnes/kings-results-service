package org.kingsski.kaas.service.scoreregional;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScoreRegionalConfig {

    @Bean
    public ScoreRegionalDao scoreRegionalDao(DaoFactory daoFactory) {
        return daoFactory.newScoreRegionalDao();
    }
}
