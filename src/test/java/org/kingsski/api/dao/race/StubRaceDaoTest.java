package org.kingsski.api.dao.race;

import org.junit.Test;
import org.kingsski.api.dao.race.RaceDao;
import org.kingsski.api.dao.race.StubRaceDao;
import org.kingsski.api.model.Race;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StubRaceDaoTest {

    private static final RaceDao SERVICE = new StubRaceDao();

    @Test
    public void getRacesAll() throws Exception {
        List<Race> races = SERVICE.getRacesAll();

        assertNotNull(races);
        assertTrue(races.isEmpty());
    }

    @Test
    public void getRacesByLeague() throws Exception {
        List<Race> races = SERVICE.getRacesByLeague("");

        assertNotNull(races);
        assertTrue(races.isEmpty());
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        List<Race> races = SERVICE.getRacesByLeagueAndRound("", "");

        assertNotNull(races);
        assertTrue(races.isEmpty());
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        List<Race> races = SERVICE.getRacesBySeasonAndLeagueAndRound("", "", "");

        assertNotNull(races);
        assertTrue(races.isEmpty());
    }

}