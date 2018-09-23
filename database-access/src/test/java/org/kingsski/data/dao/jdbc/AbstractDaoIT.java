package org.kingsski.data.dao.jdbc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
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

    @Value("${testdb.full.url}")
    private String url;

    @Value("${testdb.username}")
    private String user;

    @Value("${testdb.password}")
    private String password;

    @Value("${testdb.driver}")
    private String driver;

    protected DriverManagerDataSource dataSource;

    protected JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}