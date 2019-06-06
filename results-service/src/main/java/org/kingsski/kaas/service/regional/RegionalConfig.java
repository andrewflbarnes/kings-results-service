package org.kingsski.kaas.service.regional;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.regional.RegionalDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegionalConfig {

    @Bean
    public RegionalDao regionalDao(DaoFactory daoFactory) {
        return daoFactory.newRegionalDao();
    }
}
