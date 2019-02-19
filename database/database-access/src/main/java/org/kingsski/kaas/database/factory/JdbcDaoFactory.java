package org.kingsski.kaas.database.factory;

import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.club.jdbc.JdbcClubDao;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.competition.jdbc.JdbcCompetitionDao;
import org.kingsski.kaas.database.organisation.jdbc.JdbcOrganisationDao;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.kingsski.kaas.database.team.jdbc.JdbcTeamDao;
import org.kingsski.kaas.database.team.TeamDao;
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
    public TeamDao newTeamDao() {
        return new JdbcTeamDao(jdbcTemplate);
    }

    @Override
    public OrganisationDao newOrganisationDao() {
        return new JdbcOrganisationDao(jdbcTemplate);
    }

    @Override
    public CompetitionDao newCompetitionDao() {
        return new JdbcCompetitionDao(jdbcTemplate);
    }
}