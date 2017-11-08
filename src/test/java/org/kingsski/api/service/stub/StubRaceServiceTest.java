package org.kingsski.api.service.stub;

import org.junit.Test;
import org.kingsski.api.model.Race;
import org.kingsski.api.service.RaceService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StubRaceServiceTest {

    private static final RaceService SERVICE = new StubRaceService();

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