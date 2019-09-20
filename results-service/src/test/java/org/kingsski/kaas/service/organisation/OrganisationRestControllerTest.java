package org.kingsski.kaas.service.organisation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.organisation.Organisation;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrganisationRestController.class)
public class OrganisationRestControllerTest {

    private static final String API_ORGANISATION = "/organisation/";

    @Resource
    private MockMvc mvc;

    @MockBean
    private OrganisationService organisationService;

    @Test
    public void getOrganisations() throws Exception {
        final Organisation organisation = new Organisation();
        organisation.setName("boom");
        final List<Organisation> organisations = new ArrayList<>();
        organisations.add(organisation);

        given(organisationService.getOrganisations())
                .willReturn(organisations);

        mvc.perform(get(API_ORGANISATION))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(organisation.getName())));
    }

    @Test
    public void getOrganisationByName() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Organisation organisation = new Organisation();
        organisation.setName(name);

        given(organisationService.getOrganisationByName(not(eq(name))))
                .willReturn(null);
        given(organisationService.getOrganisationByName(name))
                .willReturn(organisation);

        mvc.perform(get(API_ORGANISATION + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(organisation.getName())));

        mvc.perform(get(API_ORGANISATION + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOrganisationById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Organisation organisation = new Organisation();
        organisation.setId(id);

        given(organisationService.getOrganisationById(not(eq(id))))
                .willReturn(null);
        given(organisationService.getOrganisationById(id))
                .willReturn(organisation);

        mvc.perform(get(API_ORGANISATION + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)organisation.getId())));

        mvc.perform(get(API_ORGANISATION + badId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addOrganisation() throws Exception {
        final String name = "The bOrg";
        final Organisation org = Organisation.builder().name(name).build();

        given(organisationService.addOrganisation(name))
                .willReturn(org);

        mvc.perform(post(API_ORGANISATION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"name\": \"%s\"}", name)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").isNumber());

        then(organisationService)
                .should(times(1))
                .addOrganisation(name);
    }

    @Test
    public void addExistingOrganisation() throws Exception {
        final String name = "The bOrg";
        final Organisation org = Organisation.builder().name(name).build();

        given(organisationService.addOrganisation(name))
                .willThrow(OrganisationAlreadyExistsException.class);

        mvc.perform(post(API_ORGANISATION)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"name\": \"%s\"}", name)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").hasJsonPath());

        then(organisationService)
                .should(times(1))
                .addOrganisation(name);
    }
}
