package org.kingsski.data.dao.jdbc;

import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.model.Individual;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcIndividualDao extends AbstractJdbcDao implements IndividualDao {

    public static final String SELECT_ALL = "jdbcIndividualSelectAll";
    public static final String SELECT_BY_DISCIPLINE = "jdbcIndividualSelectDiscipline";
    public static final String SELECT_BY_CLUB = "jdbcIndividualSelectClub";

    public JdbcIndividualDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        super(jdbcTemplate, jdbcStatements);
    }

    @Override
    public List<Individual> getAllIndividuals() {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_ALL), new Individual.IndividualMapper());
    }

    @Override
    public List<Individual> getIndividualsByDiscipline(String discipline) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_DISCIPLINE),
                new Object[] { discipline },
                new Individual.IndividualMapper());
    }

    @Override
    public List<Individual> getIndividualsByClub(String club) {
        return jdbcTemplate.query(jdbcStatements.get(SELECT_BY_CLUB),
                new Object[] { club },
                new Individual.IndividualMapper());
    }
}
