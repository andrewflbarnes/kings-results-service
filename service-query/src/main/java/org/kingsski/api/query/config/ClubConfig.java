package org.kingsski.api.query.config;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.DaoFactory;
import org.kingsski.service.ClubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ClubConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public ClubDao clubDao() {
        return daoFactory.getClubDao();
    }

    @Bean
    public ClubService clubService(ClubDao clubDao) {
        ClubService clubService = new ClubService();
        clubService.setClubDao(clubDao);

        return clubService;
    }
}
