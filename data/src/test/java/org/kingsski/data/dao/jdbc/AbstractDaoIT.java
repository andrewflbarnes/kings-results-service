package org.kingsski.data.dao.jdbc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
public abstract class AbstractDaoIT {

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcStatements;

    @Value("${db.full.url}")
    private String url;

    @Value("${db.username}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver}")
    private String driver;

    protected DriverManagerDataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}