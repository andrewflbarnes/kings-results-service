package org.kingsski.database.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public abstract class IntegrationTestConfig {

    @Value("${testdb.full.url}")
    private String url;

    @Value("${testdb.username}")
    private String user;

    @Value("${testdb.password}")
    private String password;

    @Value("${testdb.driver}")
    private String driver;

    @Value("${testdb.schema}")
    private String schema;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDefaultSchema(schema);
        dataSource.setInitialSize(3);
        return new JdbcTemplate(dataSource);
    }
}