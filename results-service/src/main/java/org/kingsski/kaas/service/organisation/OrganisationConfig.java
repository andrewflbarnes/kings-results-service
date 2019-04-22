package org.kingsski.kaas.service.organisation;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class OrganisationConfig {

    @Resource
    private DaoFactory daoFactory;

    @Bean
    public OrganisationDao organisationDao() {
        return daoFactory.newOrganisationDao();
    }
}
