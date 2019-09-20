package org.kingsski.kaas.service.club;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.club.Club;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClubRestController.class)
public class ClubRestControllerTest {

    private static final String API_CLUBS = "/club/";

    @Resource
    private MockMvc mvc;

    @MockBean
    private ClubService clubService;

    @Test
    public void getClubs() throws Exception {
        final Club club = new Club();
        club.setName("boom");
        final List<Club> clubs = new ArrayList<>();
        clubs.add(club);

        given(clubService.getClubs())
                .willReturn(clubs);

        mvc.perform(get(API_CLUBS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(club.getName())));
    }

    @Test
    public void getClubByName() throws Exception {
        final String name = "boom";
        final String badName = "pow";
        final Club club = new Club();
        club.setName(name);

        given(clubService.getClubByName(not(eq(name))))
                .willReturn(null);
        given(clubService.getClubByName(name))
                .willReturn(club);

        mvc.perform(get(API_CLUBS + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(club.getName())));

        mvc.perform(get(API_CLUBS + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getClubById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Club club = new Club();
        club.setId(id);

        given(clubService.getClubById(not(eq(id))))
                .willReturn(null);
        given(clubService.getClubById(id))
                .willReturn(club);

        mvc.perform(get(API_CLUBS + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)club.getId())));

        mvc.perform(get(API_CLUBS + badId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addClub() throws Exception {
        final String name = "boom";
        final Club club = Club.builder().name(name).id(12334L).build();
        final ObjectMapper mapper = new ObjectMapper();

        given(clubService.addClub(name))
                .willReturn(club);

        mvc.perform(post(API_CLUBS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(club)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(club.getName())))
                .andExpect(jsonPath("$.id", is((int)club.getId())));
    }

    @Test
    public void addExistingClub() throws Exception {
        final String name = "boom";
        final Club club = Club.builder().name(name).build();
        final ObjectMapper mapper = new ObjectMapper();

        given(clubService.addClub(name))
                .willThrow(ClubAlreadyExistsException.class);

        mvc.perform(post(API_CLUBS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(club)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").hasJsonPath());
    }
}
