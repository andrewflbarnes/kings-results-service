package org.kingsski.database.dao;

import org.kingsski.database.model.Organisation;

import java.util.List;

/**
 * Defines the DAO operations for the ORGANISATION view (querying)
 */
public interface OrganisationDao {

    /**
     * Get a list of all organisations
     * @return a list of organisations
     */
    List<Organisation> getOrganisations();

    /**
     * Get a organisation by id
     * @param id the id of the organisation
     * @return a organisation
     */
    Organisation getOrganisationById(long id);

    /**
     * Get a organisation by name
     * @param name the name of the organisation
     * @return a organisation
     */
    Organisation getOrganisationByName(String name);
}
