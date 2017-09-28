package kingsraceservice.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kingsraceservice.model.DummyRace;

@RestController
public class RaceResultsController {
	
	private static final List<DummyRace> RACES;
	
	static {
		List<DummyRace> dummyRaces = new ArrayList<DummyRace>();
		dummyRaces.add(new DummyRace(1, "M", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new DummyRace(2, "M", "SKUM 1", "Sheffield 1", "SKUM 1", null, null, false));
		dummyRaces.add(new DummyRace(3, "M", "Sheffield 1", "Leeds 1", "Leeds 1", null, null, false));
		dummyRaces.add(new DummyRace(4, "M", "SKUM 2", "Sheffield 2", "Sheffield 2", "final racer false start", null, false));
		dummyRaces.add(new DummyRace(5, "L", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new DummyRace(6, "L", "Sheffield 1", "DUSSC 1", "Sheffield 1", null, null, false));
		dummyRaces.add(new DummyRace(7, "B", "Sheffield 1", "Leeds 1", "Sheffield 1", null, null, false));
		
		RACES = Collections.unmodifiableList(dummyRaces);
	}
	
	private int dummyControl = 0;
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/races")
	public List<DummyRace> races(@RequestParam(value="division", defaultValue="ALL") String division, @RequestParam(value="league", defaultValue="NORTHERN") String league) {
		dummyControl++;
		if (dummyControl > RACES.size()) {
			dummyControl = 1;
		}
		
		List<DummyRace> races = new ArrayList<DummyRace>();
		
		for (DummyRace race : RACES) {
			races.add(new DummyRace(race));
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
