package org.kingsski.kaas.service.organisation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.kingsski.kaas.service.exception.EntityAlreadyExistsException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OrganisationServiceTest {

    @Mock
    private OrganisationDao organisationDao;

    private OrganisationService organisationService;

    @Before
    public void setUp() {
        this.organisationService = new OrganisationService(organisationDao);
    }

    @Bean
    public OrganisationService organisationService() {
        return new OrganisationService(organisationDao);
    }

    @Test
    public void getOrganisations() {
        final List<Organisation> organisations = new ArrayList<>();
        given(organisationDao.getOrganisations())
                .willReturn(organisations);

        List<Organisation> returnedOrganisations = organisationService.getOrganisations();

        assertEquals(organisations, returnedOrganisations);
        then(organisationDao).should(times(1)).getOrganisations();
    }

    @Test
    public void getOrganisationByName() {
        final String name = "name";
        final Organisation organisation = new Organisation();
        given(organisationDao.getOrganisationByName(name))
                .willReturn(organisation);

        Organisation returnedOrganisation = organisationService.getOrganisationByName(name);

        assertEquals(organisation, returnedOrganisation);
        then(organisationDao).should(times(1)).getOrganisationByName(name);
    }

    @Test
    public void getOrganisationById() {
        final long id = 99L;
        final Organisation organisation = new Organisation();
        given(organisationDao.getOrganisationById(id))
                .willReturn(organisation);

        Organisation returnedOrganisation = organisationService.getOrganisationById(id);

        assertEquals(organisation, returnedOrganisation);
        then(organisationDao).should(times(1)).getOrganisationById(id);
    }

    @Test
    public void addOrganisation() throws Exception {
        final String name = "Cunning Stunts";
        final Organisation org = Organisation.builder().name(name).build();

        given(organisationDao.getOrganisationByName(name))
                .willReturn(null);
        given(organisationDao.addOrganisation(name))
                .willReturn(org);

        Organisation actualOrg = organisationService.addOrganisation(name);

        then(organisationDao)
                .should(times(1))
                .getOrganisationByName(name);
        then(organisationDao)
                .should(times(1))
                .addOrganisation(name);

        assertNotNull(actualOrg);
        assertEquals(org.getName(), actualOrg.getName());
    }

    @Test(expected = EntityAlreadyExistsException.class)
    public void addExistingOrganisation() throws Exception {
        final String name = "Stunning Bunts";
        final Organisation org = Organisation.builder().name(name).build();

        given(organisationDao.getOrganisationByName(name))
                .willReturn(org);

        organisationService.addOrganisation(name);
    }
}
