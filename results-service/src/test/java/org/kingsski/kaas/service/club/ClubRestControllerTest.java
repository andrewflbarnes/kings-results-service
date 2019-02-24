package org.kingsski.kaas.service.club;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.club.Club;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClubRestController.class)
public class ClubRestControllerTest {

    private static final String API_CLUBS = "/clubs";
    private static final String API_CLUB_BY = "/club/";

    @Resource
    private MockMvc mvc;

    @MockBean
    private ClubService clubService;

    @Test
    public void getClubs() throws Exception {
        final Club club = new Club();
        club.name("boom");
        final List<Club> clubs = new ArrayList<>();
        clubs.add(club);

        given(clubService.getClubs()).willReturn(clubs);

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
        club.name(name);

        given(clubService.getClubByName(not(eq(name)))).willReturn(null);
        given(clubService.getClubByName(name)).willReturn(club);

        mvc.perform(get(API_CLUB_BY + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(club.getName())));

        mvc.perform(get(API_CLUB_BY + badName))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getClubById() throws Exception {
        final long id = 1;
        final long badId = 2;
        final Club club = new Club();
        club.id(id);

        given(clubService.getClubById(not(eq(id)))).willReturn(null);
        given(clubService.getClubById(id)).willReturn(club);

        mvc.perform(get(API_CLUB_BY + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)club.getId())));

        mvc.perform(get(API_CLUB_BY + badId))
                .andExpect(status().isNotFound());
    }
}
