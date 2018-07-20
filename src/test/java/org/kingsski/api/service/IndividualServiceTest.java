package org.kingsski.api.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.dao.IndividualDao;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link RaceService}
 */
@RunWith(MockitoJUnitRunner.class)
public class IndividualServiceTest {

    @Mock
    private IndividualDao individualDaoMock;

    private IndividualService individualService;

    @Before
    public void setUp() throws Exception {
        individualService = new IndividualService(individualDaoMock);
    }

    @Test
    public void getAllIndividuals() {
        individualService.getIndividualsAll();

        verify(individualDaoMock, times(1)).getAllIndividuals();
    }

    @Test
    public void getIndividualsByDiscipline() {
        final String discipline = "LEAGUE";

        individualService.getIndividualsByDiscipline(discipline);

        verify(individualDaoMock, times(1)).getIndividualsByDiscipline(discipline);
    }

    @Test
    public void getIndividualsByClub() {
        final String club = "CLUB";

        individualService.getIndividualsByClub(club);

        verify(individualDaoMock, times(1)).getIndividualsByClub(club);
    }
}
