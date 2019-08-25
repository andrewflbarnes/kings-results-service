package org.kingsski.kaas.database.factory;

import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.club.jdbc.JdbcClubDao;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.competition.jdbc.JdbcCompetitionDao;
import org.kingsski.kaas.database.division.DivisionDao;
import org.kingsski.kaas.database.division.jdbc.JdbcDivisionDao;
import org.kingsski.kaas.database.league.LeagueDao;
import org.kingsski.kaas.database.league.jdbc.JdbcLeagueDao;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.kingsski.kaas.database.organisation.jdbc.JdbcOrganisationDao;
import org.kingsski.kaas.database.regional.RegionalDao;
import org.kingsski.kaas.database.regional.jdbc.JdbcRegionalDao;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.kingsski.kaas.database.scoreregional.jdbc.JdbcScoreRegionalDao;
import org.kingsski.kaas.database.season.SeasonDao;
import org.kingsski.kaas.database.season.jdbc.JdbcSeasonDao;
import org.kingsski.kaas.database.team.TeamDao;
import org.kingsski.kaas.database.team.jdbc.JdbcTeamDao;
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

    @Override
    public SeasonDao newSeasonDao() {
        return new JdbcSeasonDao(jdbcTemplate);
    }

    @Override
    public DivisionDao newDivisionDao() {
        return new JdbcDivisionDao((jdbcTemplate));
    }

    @Override
    public LeagueDao newLeagueDao() {
        return new JdbcLeagueDao(jdbcTemplate);
    }

    @Override
    public RegionalDao newRegionalDao() {
        return new JdbcRegionalDao(jdbcTemplate);
    }

    @Override
    public ScoreRegionalDao newScoreRegionalDao() {
        return new JdbcScoreRegionalDao(jdbcTemplate);
    }
}
