package kingsraceservice.dummy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kingsraceservice.api.service.RaceService;
import kingsraceservice.model.Race;

public class DummyRaceService implements RaceService {
	
	private static final List<Race> RACES;
	
	static {
		List<Race> dummyRaces = new ArrayList<Race>();
		dummyRaces.add(new Race(1, "M", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race(2, "M", "SKUM 1", "Sheffield 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race(3, "M", "Sheffield 1", "Leeds 1", "Leeds 1", null, null, false));
		dummyRaces.add(new Race(4, "M", "SKUM 2", "Sheffield 2", "Sheffield 2", "final racer false start", null, false));
		dummyRaces.add(new Race(5, "L", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race(6, "L", "Sheffield 1", "DUSSC 1", "Sheffield 1", null, null, false));
		dummyRaces.add(new Race(7, "B", "Sheffield 1", "Leeds 1", "Sheffield 1", null, null, false));
		
		RACES = Collections.unmodifiableList(dummyRaces);
	}
	
	private int dummyControl = 0;
	

	@Override
	public List<Race> getRacesAll() {
		return cycleRaces();
	}

	@Override
	public List<Race> getRacesByLeague(String league) {
		return cycleRaces();
	}

	@Override
	public List<Race> getRacesByLeagueAndRound(String round, String division) {
		return cycleRaces();
	}
	
	private List<Race> cycleRaces() {
		
		dummyControl++;
		if (dummyControl > RACES.size()) {
			dummyControl = 1;
		}
		
		List<Race> races = new ArrayList<Race>();
		
		for (Race race : RACES) {
			races.add(new Race(race));
		}
		
		for (int i = dummyControl - 1; i < races.size(); i++) {
			races.get(i).setWinner(null);
			races.get(i).setTeamOneDsq(null);
			races.get(i).setTeamTwoDsq(null);
		}

		races.get(dummyControl - 1).setNext(true);
		
		return races;
	}

}
