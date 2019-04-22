package org.kingsski.kaas.service.organisation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.organisation.Organisation;
import org.kingsski.kaas.database.organisation.OrganisationDao;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class OrganisationServiceTest {

    @TestConfiguration
    static class OrganisationServiceTestConfiguration {
        @Bean
        public OrganisationService organisationService() {
            return new OrganisationService();
        }
    }

    @MockBean
    private OrganisationDao organisationDao;

    @Resource
    private OrganisationService organisationService;

    @Test
    public void getOrganisations() {
        final List<Organisation> organisations = new ArrayList<>();
        given(organisationDao.getOrganisations()).willReturn(organisations);

        List<Organisation> returnedOrganisations = organisationService.getOrganisations();

        assertEquals(organisations, returnedOrganisations);
        then(organisationDao).should(times(1)).getOrganisations();
    }

    @Test
    public void getOrganisationByName() {
        final String name = "name";
        final Organisation organisation = new Organisation();
        given(organisationDao.getOrganisationByName(name)).willReturn(organisation);

        Organisation returnedOrganisation = organisationService.getOrganisationByName(name);

        assertEquals(organisation, returnedOrganisation);
        then(organisationDao).should(times(1)).getOrganisationByName(name);
    }

    @Test
    public void getOrganisationById() {
        final long id = 99L;
        final Organisation organisation = new Organisation();
        given(organisationDao.getOrganisationById(id)).willReturn(organisation);

        Organisation returnedOrganisation = organisationService.getOrganisationById(id);

        assertEquals(organisation, returnedOrganisation);
        then(organisationDao).should(times(1)).getOrganisationById(id);
    }
}
