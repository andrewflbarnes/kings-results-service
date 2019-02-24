package org.kingsski.kaas.service.config;

import org.kingsski.kaas.database.factory.DaoFactory;
import org.kingsski.kaas.database.factory.JdbcDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Database configuration for JDBC connections
 */
@Profile("jdbc")
@Configuration
public class DaoJdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DaoFactory daoFactory(JdbcTemplate jdbcTemplate) {
        return new JdbcDaoFactory(jdbcTemplate);
    }
}
