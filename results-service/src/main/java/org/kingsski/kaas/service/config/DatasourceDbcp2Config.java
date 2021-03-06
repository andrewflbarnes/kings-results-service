package org.kingsski.kaas.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Database configuration for DBCP2 connection pooling in DataSources
 */
@Profile("dbcp2")
@Configuration
public class DatasourceDbcp2Config {

    @Value("${db.driver}")
    private String driver;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.url}")
    private String url;

    @Value("${db.schema}")
    private String schema;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDefaultSchema(schema);
        dataSource.setInitialSize(3);

        return dataSource;
    }
}
