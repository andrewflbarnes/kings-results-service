package org.kingsski.query.config;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.DaoFactory;
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
