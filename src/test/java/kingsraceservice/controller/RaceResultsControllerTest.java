package kingsraceservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import kingsraceservice.api.service.RaceService;

/**
 * Test class for {@link RaceResultsController}
 */
@RunWith(MockitoJUnitRunner.class)
public class RaceResultsControllerTest {
	
	@Mock
	private RaceService raceServiceMock;
	private final RaceResultsController controller = new RaceResultsController();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		controller.setRaceService(raceServiceMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllRaces() {
		controller.races();
		
		verify(raceServiceMock, times(1)).getRacesAll();
	}
	
	@Test
	public void testGetRacesByLeague() {
		final String league = "LEAGUE";
		
		controller.racesByLeague(league);
		
		verify(raceServiceMock, times(1)).getRacesByLeague(league);
	}
	
	@Test
	public void testGetRacesByLeagueAndRound() {
		final String league = "LEAGUE";
		final String round = "ROUND";
		
		controller.racesByLeagueByRound(league, round);
		
		verify(raceServiceMock, times(1)).getRacesByLeagueAndRound(league, round);
	}
	
	@Test
	public void testGetHistoricRaces() {
		final String league = "LEAGUE";
		final String season = "SEASON";
		final String round = "ROUND";
		
		controller.racesByLeagueBySeasonByRound(season, league, round);
		
		verify(raceServiceMock, times(1)).getRacesBySeasonAndLeagueAndRound(season, league, round);
	}
}
