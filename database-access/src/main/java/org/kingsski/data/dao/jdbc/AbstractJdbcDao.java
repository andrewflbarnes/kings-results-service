package org.kingsski.data.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public abstract class AbstractJdbcDao {

    protected JdbcTemplate jdbcTemplate;
    protected Map<String, String> jdbcStatements;

    public AbstractJdbcDao(JdbcTemplate jdbcTemplate, Map<String, String> jdbcStatements) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcStatements = jdbcStatements;
    }
}
