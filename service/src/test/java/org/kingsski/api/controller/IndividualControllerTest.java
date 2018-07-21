package org.kingsski.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.service.IndividualService;
import org.kingsski.api.service.RaceService;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for {@link RaceController}
 */
@RunWith(MockitoJUnitRunner.class)
public class IndividualControllerTest {

    @Mock
    private IndividualService individualServiceMock;

    private IndividualController controller;

    @Before
    public void setUp() throws Exception {
        controller = new IndividualController(individualServiceMock);
    }

    @Test
    public void testGetIndividualsAll() {
        controller.individuals(null);

        verify(individualServiceMock, times(1)).getIndividualsAll();
    }

    @Test
    public void testGetIndividualsByClub() {
        final String club = "club";

        controller.individuals(club);

        verify(individualServiceMock, times(1)).getIndividualsByClub(club);
    }

    @Test
    public void getIndividualsByDiscipline() {
        final String discipline = "discipline";

        controller.individualsByDiscipline(discipline);

        verify(individualServiceMock, times(1)).getIndividualsByDiscipline(discipline);
    }
}
