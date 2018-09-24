package org.kingsski.api.test.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.model.Individual;
import org.kingsski.data.model.Race;
import org.kingsski.data.model.Team;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ImportResource("classpath:jdbc-config.xml")
public class TestJdbcDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig(JdbcTemplate jdbcTemplate) {
        // TODO
        return new DaoFactory() {
            @Override
            public String getDbProfile() {
                return null;
            }
        };
    }

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcSharedStatements;

    private List<Team> teams = new ArrayList<>();

    private List<Individual> individuals = new ArrayList<>();

    private List<Race> races = new ArrayList<>();

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return Mockito.mock(JdbcTemplate.class);
    }
}
