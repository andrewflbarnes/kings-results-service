package org.kingsski.kaas.database.scoreregional.jdbc;

import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * JDBC implementation of {@link ScoreRegionalDao}
 */
public class JdbcScoreRegionalDao implements ScoreRegionalDao {

    private static final String SELECT_ALL = "SELECT * FROM score_regional";


    private JdbcTemplate jdbcTemplate;

    public JdbcScoreRegionalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ScoreRegional> getScoreRegionals() {
        return jdbcTemplate.query(SELECT_ALL, new Object[]{}, new ScoreRegionalMapper());
    }
}
