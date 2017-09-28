package kingsraceservice.api.service;

import java.util.List;

import kingsraceservice.model.Race;

public interface RaceService {
	
	List<Race> getRacesAll();
	
	List<Race> getRacesByLeague(String league);
	
	List<Race> getRacesByLeagueAndRound(String league, String round);

}
