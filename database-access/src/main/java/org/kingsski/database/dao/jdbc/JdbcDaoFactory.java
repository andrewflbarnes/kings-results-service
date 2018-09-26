package org.kingsski.database.dao.jdbc;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.DaoFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDaoFactory implements DaoFactory {

    private JdbcTemplate jdbcTemplate;

    public JdbcDaoFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ClubDao getClubDao() {
        return new JdbcClubDao(jdbcTemplate);
    }
}
