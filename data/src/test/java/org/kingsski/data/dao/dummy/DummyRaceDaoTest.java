package org.kingsski.data.dao.dummy;

import org.junit.Test;
import org.kingsski.data.dao.RaceDao;
import org.kingsski.data.model.Race;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DummyRaceDaoTest {

    private static final RaceDao SERVICE = new DummyRaceDao();

    @Test
    public void getRacesAll() throws Exception {
        List<Race> races = SERVICE.getRacesAll();

        assertNotNull(races);
        assertFalse(races.isEmpty());
    }

    @Test
    public void getRacesByLeague() throws Exception {
        List<Race> races = SERVICE.getRacesByLeague("LEAGUE");
        List<Race> races2 = SERVICE.getRacesByLeague("OTHERLEAGUE");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        List<Race> races = SERVICE.getRacesByLeagueAndRound("LEAGUE", "ROUND");
        List<Race> races2 = SERVICE.getRacesByLeagueAndRound("OTHERLEAGUE", "OTHERROUND");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        List<Race> races = SERVICE.getRacesBySeasonAndLeagueAndRound("SEASON", "LEAGUE", "ROUND");
        List<Race> races2 = SERVICE.getRacesBySeasonAndLeagueAndRound("OTHERSEASON", "OTHERLEAGUE", "OTHERROUND");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

}