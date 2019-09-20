package org.kingsski.kaas.service.competition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
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
public class CompetitionServiceTest {

    @TestConfiguration
    static class CompetitionServiceTestConfiguration {
        @Bean
        public CompetitionService competitionService() {
            return new CompetitionService();
        }
    }

    @MockBean
    private CompetitionDao competitionDao;

    @Resource
    private CompetitionService competitionService;

    @Test
    public void getCompetitions() {
        final List<Competition> competitions = new ArrayList<>();
        given(competitionDao.getCompetitions())
                .willReturn(competitions);

        List<Competition> returnedCompetitions = competitionService.getCompetitions();

        assertEquals(competitions, returnedCompetitions);
        then(competitionDao).should(times(1)).getCompetitions();
    }

    @Test
    public void getCompetitionByName() {
        final String name = "name";
        final Competition competition = new Competition();
        given(competitionDao.getCompetitionByName(name))
                .willReturn(competition);

        Competition returnedCompetition = competitionService.getCompetitionByName(name);

        assertEquals(competition, returnedCompetition);
        then(competitionDao).should(times(1)).getCompetitionByName(name);
    }

    @Test
    public void getCompetitionById() {
        final long id = 99L;
        final Competition competition = new Competition();
        given(competitionDao.getCompetitionById(id))
                .willReturn(competition);

        Competition returnedCompetition = competitionService.getCompetitionById(id);

        assertEquals(competition, returnedCompetition);
        then(competitionDao).should(times(1)).getCompetitionById(id);
    }
}
