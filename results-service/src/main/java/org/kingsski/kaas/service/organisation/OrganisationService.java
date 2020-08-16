package org.kingsski.kaas.service.organisation;

import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service defines and implements operations for {@link Organisation}s.
 * Any APIs, resources or other services wanting to service or
 * mutate {@link Organisation}s should do so through this service and
 * not through other means (e.g. more directly through DAOs)
 */
@Service
public class OrganisationService {

    private final OrganisationDao organisationDao;

    public OrganisationService(OrganisationDao organisationDao) {
        this.organisationDao = organisationDao;
    }

    /**
     * Get all {@link Organisation}s
     *
     * @return a {@link List} of {@link Organisation}s
     */
    public List<Organisation> getOrganisations() {
        return organisationDao.getOrganisations();
    }

    /**
     * Get a {@link Organisation} by name
     *
     * @param name the name of the organisation to get
     * @return a {@link Organisation}
     */
    public Organisation getOrganisationByName(String name) {
        return organisationDao.getOrganisationByName(name);
    }

    /**
     * Get a {@link Organisation} by id
     *
     * @param id the id of the organisation to get
     * @return a {@link Organisation}
     */
    public Organisation getOrganisationById(long id) {
        return organisationDao.getOrganisationById(id);
    }

    /**
     * Add a new {@link Organisation}
     *
     * @param name the name of the {@link Organisation} to add
     * @return a {@link Organisation}
     * @throws EntityAlreadyExistsException
     */
    public Organisation addOrganisation(String name) throws EntityAlreadyExistsException {
        Organisation org = organisationDao.getOrganisationByName(name);
        if (org != null) {
            throw new EntityAlreadyExistsException("organisation", "name", name);
        }
        return organisationDao.addOrganisation(name);
    }
}
