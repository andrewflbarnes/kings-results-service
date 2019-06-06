package org.kingsski.kaas.database.factory;

import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.league.LeagueDao;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.kingsski.kaas.database.scoreregional.ScoreRegionalDao;
import org.kingsski.kaas.database.season.SeasonDao;
import org.kingsski.kaas.database.team.TeamDao;

/**
 * Defines a factory for providing DAOs
 */
public interface DaoFactory {

    /**
     * Get a new instance of a {@link ClubDao}
     *
     * @return a DAO
     */
    ClubDao newClubDao();

    /**
     * Get a new instance of a {@link TeamDao}
     *
     * @return a DAO
     */
    TeamDao newTeamDao();

    /**
     * Get a new instance of a {@link OrganisationDao}
     *
     * @return a DAO
     */
    OrganisationDao newOrganisationDao();

    /**
     * Get a new instance of a {@link CompetitionDao}
     *
     * @return a DAO
     */
    CompetitionDao newCompetitionDao();

    /**
     * Get a new instance of a {@link SeasonDao}
     * @return a DAO
     */
    SeasonDao newSeasonDao();

    /**
     * Get a new instance of a {@link LeagueDao}
     * @return a DAO
     */
    LeagueDao newLeagueDao();

    /**
     * Get a new instance of a {@link ScoreRegionalDao}
     *
     * @return a DAO
     */
    ScoreRegionalDao newScoreRegionalDao();
}
