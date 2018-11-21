package org.kingsski.database.dao.jdbc;

import org.kingsski.database.dao.ClubDao;
import org.kingsski.database.dao.DaoFactory;
import org.kingsski.database.dao.OrganisationDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Implementation providing DAOs backed by JDBC
 */
public class JdbcDaoFactory implements DaoFactory {

    private JdbcTemplate jdbcTemplate;

    public JdbcDaoFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ClubDao newClubDao() {
        return new JdbcClubDao(jdbcTemplate);
    }

    @Override
    public OrganisationDao newOrganisationDao() {
        return new JdbcOrganisationDao(jdbcTemplate);
    }
}
