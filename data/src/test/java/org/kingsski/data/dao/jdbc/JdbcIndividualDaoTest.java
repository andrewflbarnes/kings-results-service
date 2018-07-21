package org.kingsski.data.dao.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.model.Individual;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JdbcIndividualDaoTest {

    private static final String STMT_ALL = "SA";
    private static final String STMT_CLUB = "SBC";
    private static final String STMT_DISC = "SBD";
    private static final String CLUB = "club";
    private static final String DISCIPLINE = "discipline";

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private List<Individual> individuals;

    private Map<String, String> jdbcStatements = new HashMap<>();

    @InjectMocks
    private IndividualDao individualDao = new JdbcIndividualDao(jdbcTemplate, jdbcStatements);

    @Test
    public void getAllIndividuals() throws Exception {
        jdbcStatements.put(JdbcIndividualDao.SELECT_ALL, STMT_ALL);
        when(jdbcTemplate.query(eq(STMT_ALL), any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        List<Individual> actual = individualDao.getAllIndividuals();

        assertEquals(individuals, actual);
    }

    @Test
    public void getIndividualsByClub() throws Exception {
        jdbcStatements.put(JdbcIndividualDao.SELECT_BY_CLUB, STMT_CLUB);
        when(jdbcTemplate.query(eq(STMT_CLUB), eq(new Object[] { CLUB }), any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        List<Individual> actual = individualDao.getIndividualsByClub(CLUB);

        assertEquals(individuals, actual);
    }

    @Test
    public void getIndividualsByDiscipline() throws Exception {
        jdbcStatements.put(JdbcIndividualDao.SELECT_BY_DISCIPLINE, STMT_DISC);
        when(jdbcTemplate.query(eq(STMT_DISC), eq(new Object[] { DISCIPLINE }), any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        List<Individual> actual = individualDao.getIndividualsByDiscipline(DISCIPLINE);

        assertEquals(individuals, actual);
    }
}