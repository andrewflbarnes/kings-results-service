package org.kingsski.api.service.dummy;

import org.junit.Test;
import org.kingsski.api.model.DisplayableRace;
import org.kingsski.api.service.RaceService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DummyRaceServiceTest {

    private static final RaceService SERVICE = new DummyRaceService();

    @Test
    public void getRacesAll() throws Exception {
        List<DisplayableRace> races = SERVICE.getRacesAll();

        assertNotNull(races);
        assertFalse(races.isEmpty());
    }

    @Test
    public void getRacesByLeague() throws Exception {
        List<DisplayableRace> races = SERVICE.getRacesByLeague("LEAGUE");
        List<DisplayableRace> races2 = SERVICE.getRacesByLeague("OTHERLEAGUE");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

    @Test
    public void getRacesByLeagueAndRound() throws Exception {
        List<DisplayableRace> races = SERVICE.getRacesByLeagueAndRound("LEAGUE", "ROUND");
        List<DisplayableRace> races2 = SERVICE.getRacesByLeagueAndRound("OTHERLEAGUE", "OTHERROUND");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

    @Test
    public void getRacesBySeasonAndLeagueAndRound() throws Exception {
        List<DisplayableRace> races = SERVICE.getRacesBySeasonAndLeagueAndRound("SEASON", "LEAGUE", "ROUND");
        List<DisplayableRace> races2 = SERVICE.getRacesBySeasonAndLeagueAndRound("OTHERSEASON", "OTHERLEAGUE", "OTHERROUND");

        assertNotNull(races);
        assertFalse(races.isEmpty());

        assertNotNull(races2);
        assertFalse(races2.isEmpty());

        assertEquals(races, races2);
    }

}