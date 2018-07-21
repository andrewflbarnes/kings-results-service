package org.kingsski.data.dao.jdbc;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.IndividualDao;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.dao.TeamDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class JdbcDaoFactory implements DaoFactory {

    private JdbcTemplate jdbcTemplate;
    private Map<String, String> jdbcSharedStatements;

    public JdbcDaoFactory(JdbcTemplate jdbcTemplate, Map<String, String> jdbcSharedStatements) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcSharedStatements = jdbcSharedStatements;
    }

    @Override
    public String getDbProfile() {
        return "jdbc";
    }

    @Override
    public TeamDao teamDao() {
        return new JdbcTeamDao(jdbcTemplate, jdbcSharedStatements);
    }

    @Override
    public RaceDao raceDao() {
        return new JdbcRaceDao(jdbcTemplate, jdbcSharedStatements);
    }

    @Override
    public IndividualDao individualDao() {
        return new JdbcIndividualDao(jdbcTemplate, jdbcSharedStatements);
    }
}
