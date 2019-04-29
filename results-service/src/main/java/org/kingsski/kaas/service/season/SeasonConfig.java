package org.kingsski.kaas.service.season;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.season.SeasonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeasonConfig {


    @Bean
    public SeasonDao seasonDao(DaoFactory daoFactory) {
        return daoFactory.newSeasonDao();
    }
}
