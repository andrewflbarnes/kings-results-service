package org.kingsski.api.dao.jdbc;

import org.kingsski.api.dao.DaoFactory;
import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.stub.StubRaceDao;
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
