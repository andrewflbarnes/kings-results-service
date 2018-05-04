package org.kingsski.api.dao.jdbc;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.model.Individual;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcIndividualDao implements IndividualDao {

    public static final String SELECT_ALL = "jdbcIndividualSelectAll";
    public static final String SELECT_BY_DISCIPLINE = "jdbcIndividualSelectDiscipline";
    public static final String SELECT_BY_CLUB = "jdbcIndividualSelectClub";

    private JdbcTemplate jdbcTemplate;
    private Map<String, String> jdbcStatements;

    public JdbcIndividualDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcStatements = jdbcStatements;
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
