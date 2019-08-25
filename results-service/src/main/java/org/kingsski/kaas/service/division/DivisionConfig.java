package org.kingsski.kaas.service.division;

import org.kingsski.kaas.database.division.DivisionDao;
import org.kingsski.kaas.database.factory.DaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DivisionConfig {

    @Bean
    public DivisionDao divisionDao(DaoFactory daoFactory) {
        return daoFactory.newDivisionDao();
    }
}
