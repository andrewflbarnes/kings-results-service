package kingsraceservice.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RaceTest {

	private static final int NO_1 = 1;
	private static final int NO_2 = 2;
	private static final String TEAM_1 = "team1";
	private static final String TEAM_2 = "team2";
	private static final String TEAM_3 = "team3";
	private static final String DIV_M = "M";
	private static final String DIV_L = "L";
	private static final String DUMMY = "DUMMY";
	private static final String LEAGUE_N = "Northern";
	private static final String LEAGUE_S = "Southern";
	private static final String RND_1 = "1";
	private static final String RND_2 = "2";
	private static final String SET_1 = "1";
	private static final String SET_K = "Knockouts";
	
	// Base race
	private static final Race RACE_1A = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// Identical to 1A
	private static final Race RACE_1B = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// Identical to 1A other than transient fields
	private static final Race RACE_1C = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, DUMMY, DUMMY, DUMMY, true);
	// Different league
	private static final Race RACE_1U = new Race(LEAGUE_S, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// Different round
	private static final Race RACE_1V = new Race(LEAGUE_N, RND_2, SET_K, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// Different set
	private static final Race RACE_1W = new Race(LEAGUE_N, RND_1, SET_1, NO_1, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// Different division
	private static final Race RACE_1X = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_L, TEAM_1, TEAM_2, null, null, null, false);
	// Different team 1
	private static final Race RACE_1Y = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_3, TEAM_2, null, null, null, false);
	// Different team 2
	private static final Race RACE_1Z = new Race(LEAGUE_N, RND_1, SET_K, NO_1, DIV_M, TEAM_1, TEAM_3, null, null, null, false);
	// Different race number
	private static final Race RACE_2A = new Race(LEAGUE_N, RND_1, SET_K, NO_2, DIV_M, TEAM_1, TEAM_2, null, null, null, false);
	// all null
	private static final Race RACE_NULL = new Race(null, null, null, 0, null, null, null, null, null, null, false);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testConstructor() {
		final String winner = "WINNER";
		final String dsq1 = "DSQ 1";
		final String dsq2 = "DSQ_2";
		final boolean next = true;
		
		Race race = new Race(LEAGUE_N, RND_1, SET_1, NO_1, DIV_L, TEAM_1, TEAM_2, winner, dsq1, dsq2, next);

		assertEquals(LEAGUE_N, race.getLeague());
		assertEquals(RND_1, race.getRound());
		assertEquals(SET_1, race.getSet());
		assertEquals(NO_1, race.getRaceNo());
		assertEquals(DIV_L, race.getDivision());
		assertEquals(TEAM_1, race.getTeamOne());
		assertEquals(TEAM_2, race.getTeamTwo());
		assertEquals(dsq1, race.getTeamOneDsq());
		assertEquals(dsq2, race.getTeamTwoDsq());
		assertEquals(winner, race.getWinner());
		assertEquals(next, race.isNext());
	}
	
	@Test
	public void testCopyOfConstructor() {
		Race copy = new Race(RACE_1C);
		
		assertEquals(RACE_1C, copy);

		assertEquals(RACE_1C.getLeague(), copy.getLeague());
		assertEquals(RACE_1C.getRound(), copy.getRound());
		assertEquals(RACE_1C.getSet(), copy.getSet());
		assertEquals(RACE_1C.getRaceNo(), copy.getRaceNo());
		assertEquals(RACE_1C.getDivision(), copy.getDivision());
		assertEquals(RACE_1C.getTeamOne(), copy.getTeamOne());
		assertEquals(RACE_1C.getTeamTwo(), copy.getTeamTwo());
		assertEquals(RACE_1C.getTeamOneDsq(), copy.getTeamOneDsq());
		assertEquals(RACE_1C.getTeamTwoDsq(), copy.getTeamTwoDsq());
		assertEquals(RACE_1C.getWinner(), copy.getWinner());
		assertEquals(RACE_1C.isNext(), copy.isNext());
	}

	@Test
	public void testEquals() {
		assertEquals(RACE_1A, RACE_1A);
		
		assertEquals(RACE_1A, RACE_1B);
		assertEquals(RACE_1B, RACE_1A);
		
		assertEquals(RACE_1A, RACE_1C);
		assertEquals(RACE_1C, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1U);
		assertNotEquals(RACE_1U, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1V);
		assertNotEquals(RACE_1V, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1W);
		assertNotEquals(RACE_1W, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1X);
		assertNotEquals(RACE_1X, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1Y);
		assertNotEquals(RACE_1Y, RACE_1A);

		assertNotEquals(RACE_1A, RACE_1Z);
		assertNotEquals(RACE_1Z, RACE_1A);

		assertNotEquals(RACE_1A, RACE_2A);
		assertNotEquals(RACE_2A, RACE_1A);
		
		assertNotEquals(RACE_1A, RACE_NULL);
		assertNotEquals(RACE_NULL, RACE_1A);
	}
}
