package org.kingsski.data.dao.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.model.Individual;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ImportResource("classpath:jdbc-config.xml")
@TestPropertySource("test.properties")
public class JdbcIndividualDaoIT {

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcStatements;

    @Resource
    private JdbcTemplate jdbcTemplate;

    private IndividualDao individualDao;

    @Before
    public void setUp() throws Exception {
        individualDao = new JdbcIndividualDao(jdbcTemplate, jdbcStatements);
    }

    @Test
    public void getAllIndividuals() throws Exception {
//        jdbcStatements.put(JdbcIndividualDao.SELECT_ALL, STMT_ALL);
//        when(jdbcTemplate.query(eq(STMT_ALL), any(Individual.IndividualMapper.class)))
//                .thenReturn(individuals);
//
//        List<Individual> actual = individualDao.getAllIndividuals();
//
//        assertEquals(individuals, actual);
    }

    @Test
    public void getIndividualsByClub() throws Exception {
//        jdbcStatements.put(JdbcIndividualDao.SELECT_BY_CLUB, STMT_CLUB);
//        when(jdbcTemplate.query(eq(STMT_CLUB), eq(new Object[] { CLUB }), any(Individual.IndividualMapper.class)))
//                .thenReturn(individuals);
//
//        List<Individual> actual = individualDao.getIndividualsByClub(CLUB);
//
//        assertEquals(individuals, actual);
    }

    @Test
    public void getIndividualsByDiscipline() throws Exception {
//        jdbcStatements.put(JdbcIndividualDao.SELECT_BY_DISCIPLINE, STMT_DISC);
//        when(jdbcTemplate.query(eq(STMT_DISC), eq(new Object[] { DISCIPLINE }), any(Individual.IndividualMapper.class)))
//                .thenReturn(individuals);
//
//        List<Individual> actual = individualDao.getIndividualsByDiscipline(DISCIPLINE);
//
//        assertEquals(individuals, actual);
    }
}