package org.kingsski.api.query.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.kingsski.database.dao.DaoFactory;
import org.kingsski.database.dao.jdbc.JdbcDaoFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:database.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:database.yaml", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:database.yml", ignoreResourceNotFound = true)
})
public class JdbcConfig {

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

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DaoFactory daoFactory(JdbcTemplate jdbcTemplate) {
        return new JdbcDaoFactory(jdbcTemplate);
    }
}
