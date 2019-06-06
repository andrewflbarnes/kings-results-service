package org.kingsski.kaas.service.league;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.league.LeagueDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeagueConfig {


    @Bean
    public LeagueDao leagueDao(DaoFactory daoFactory) {
        return daoFactory.newLeagueDao();
    }
}
