package org.kingsski.api.query.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.jdbc.JdbcDaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@Profile("jdbc")
public class JdbcDaoConfig {

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcSharedStatements;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Bean("delegate")
    public DaoFactory daoDelegateConfig() {
        return new JdbcDaoFactory(jdbcTemplate, jdbcSharedStatements);
    }
}