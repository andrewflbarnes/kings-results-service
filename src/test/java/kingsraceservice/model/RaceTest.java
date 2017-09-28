package kingsraceservice.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RaceTest {

	private static final String TEAM_ONE = "team1";
	private static final String TEAM_TWO = "team2";
	private static final String TEAM_THREE = "team3";
	private static final String MIXED = "M";
	private static final String LADIES = "L";
	private static final String DUMMY = "DUMMY";
	private static final Race RACE_1A = new Race(1, MIXED, TEAM_ONE, TEAM_TWO, null, null, null, false);
	private static final Race RACE_1B = new Race(1, MIXED, TEAM_ONE, TEAM_TWO, null, null, null, false);
	private static final Race RACE_1C = new Race(1, MIXED, TEAM_ONE, TEAM_TWO, DUMMY, DUMMY, DUMMY, true);
	private static final Race RACE_1X = new Race(1, LADIES, TEAM_ONE, TEAM_TWO, null, null, null, false);
	private static final Race RACE_1Y = new Race(1, MIXED, TEAM_THREE, TEAM_TWO, null, null, null, false);
	private static final Race RACE_1Z = new Race(1, MIXED, TEAM_ONE, TEAM_THREE, null, null, null, false);
	private static final Race RACE_2 = new Race(2, MIXED, TEAM_ONE, TEAM_TWO, null, null, null, false);
	private static final Race RACE_NULL = new Race(0, null, null, null, null, null, null, false);

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
	public void testCopyOfConstructor() {
		Race copy = new Race(RACE_1C);
		
		assertTrue(RACE_1C.equals(copy));
		
		assertTrue(RACE_1C.getRaceNo() == copy.getRaceNo());
		assertTrue(RACE_1C.getDivision().equals(copy.getDivision()));
		assertTrue(RACE_1C.getTeamOne().equals(copy.getTeamOne()));
		assertTrue(RACE_1C.getTeamTwo().equals(copy.getTeamTwo()));
		assertTrue(RACE_1C.getTeamOneDsq().equals(copy.getTeamOneDsq()));
		assertTrue(RACE_1C.getTeamTwoDsq().equals(copy.getTeamTwoDsq()));
		assertTrue(RACE_1C.getWinner().equals(copy.getWinner()));
		assertTrue(RACE_1C.isNext() == copy.isNext());
	}

	@Test
	public void testEquals() {
		assertTrue(RACE_1A.equals(RACE_1A));
		
		assertTrue(RACE_1A.equals(RACE_1B));
		assertTrue(RACE_1B.equals(RACE_1A));
		
		assertTrue(RACE_1A.equals(RACE_1C));
		assertTrue(RACE_1C.equals(RACE_1A));

		assertFalse(RACE_1A.equals(RACE_1X));
		assertFalse(RACE_1X.equals(RACE_1A));

		assertFalse(RACE_1A.equals(RACE_1Y));
		assertFalse(RACE_1Y.equals(RACE_1A));

		assertFalse(RACE_1A.equals(RACE_1Z));
		assertFalse(RACE_1Z.equals(RACE_1A));

		assertFalse(RACE_1A.equals(RACE_2));
		assertFalse(RACE_2.equals(RACE_1A));
		
		assertFalse(RACE_1A.equals(RACE_NULL));
		assertFalse(RACE_NULL.equals(RACE_1A));
	}

}
