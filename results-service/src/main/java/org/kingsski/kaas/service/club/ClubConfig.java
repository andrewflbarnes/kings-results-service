package org.kingsski.kaas.service.club;

import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.factory.DaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ClubConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public ClubDao clubDao() {
        return daoFactory.newClubDao();
    }
}
