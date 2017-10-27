package org.kingsski.api.races.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kingsski.api.races.controller.service.KingsApiRaceService;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test class for {@link KingsApiRaceController}
 */
@RunWith(MockitoJUnitRunner.class)
public class KingsApiRaceControllerTest {
	
	@Mock
	private KingsApiRaceService raceServiceMock;
	private final KingsApiRaceController controller = new KingsApiRaceController();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		controller.setKingsApiRaceService(raceServiceMock);
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
