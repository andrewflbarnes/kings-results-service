package org.kingsski.kaas.database.factory;

import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.kingsski.kaas.database.organisation.OrganisationDao;
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
}
