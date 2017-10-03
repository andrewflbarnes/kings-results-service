package org.kingsski.raceservice.dummy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kingsski.raceservice.api.service.RaceService;
import org.kingsski.raceservice.model.Race;

/**
 * A dummy implementation of the {@link RaceService}  interface. This class
 * has a single static list of {@link Race}s which it returns for all
 * method calls.
 * 
 * On each call it will "cycle" through the races causing the in progress
 * race to move ahead one and all previous races to have a winner set.
 * 
 * Cycling past the last race causes the list to "reset" and the current
 * race will be set to the first again.
 * 
 * This should be used for localised testing only.
 * 
 * @author Barnesly
 *
 */
public class DummyRaceService implements RaceService {
	
	private static final List<Race> RACES;
	
	/**
	 * Statoc initialiser for the {@code RACES} variable
	 */
	static {
		List<Race> dummyRaces = new ArrayList<Race>();
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 1, "M", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 2, "M", "SKUM 1", "Sheffield 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 3, "M", "Sheffield 1", "Leeds 1", "Leeds 1", null, null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 4, "M", "SKUM 2", "Sheffield 2", "Sheffield 2", "final racer false start", null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 5, "L", "SKUM 1", "Leeds 1", "SKUM 1", null, null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 6, "L", "Sheffield 1", "DUSSC 1", "Sheffield 1", null, null, false));
		dummyRaces.add(new Race("Northern", "1", "Knockouts", 7, "B", "Sheffield 1", "Leeds 1", "Sheffield 1", null, null, false));
		
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

	@Override
	public List<Race> getRacesBySeasonAndLeagueAndRound(String season, String league, String round) {
		return cycleRaces();
	}
	
	/**
	 * Main control method which increments the current race and sets
	 * the winner for previous races
	 * 
	 * @return the {@code RACES} list of {@link Race}s while moving the
	 * current race ahead one and setting the winner of the previous race
	 */
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
