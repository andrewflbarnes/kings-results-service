package org.kingsski.kaas.service.competition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.competition.CompetitionDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CompetitionServiceTest {

    @Mock
    private CompetitionDao competitionDao;

    private CompetitionService competitionService;

    @Before
    public void setUp() {
        this.competitionService = new CompetitionService(competitionDao);
    }

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
