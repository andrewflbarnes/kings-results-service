package org.kingsski.api.model;

import javax.validation.constraints.NotNull;

/**
 * This class represents a single displayable Kings race.
 * 
 * This class is designed as a DTO/VO only and is not
 * appropriate for processing actual races!
 */
public class Race {

	private String league;
	private String round;
	private String set;
	private int raceNo;
	private String division;
	private String teamOne;
	private String teamTwo;
	private String winner;
	private String teamOneDsq;
	private String teamTwoDsq;
	private boolean next;

	/**
	 * Standard constructor for a new {@link Race}
	 * 
	 * @param raceNo The number of this race in the current round
	 * @param division The division this race belongs to
	 * @param teamOne The first team competing
	 * @param teamTwo The second team competing
	 * @param winner The winner of the race
	 * @param teamOneDsq The reason the first team was disqualified
	 * @param teamTwoDsq The reason the second team was disqualified
	 * @param next true if this is the next race being run, false otherwise
	 */
	public Race(@NotNull String league, @NotNull String round, @NotNull String set, int raceNo,
			@NotNull String division, @NotNull String teamOne, @NotNull String teamTwo,
			String winner, String teamOneDsq, String teamTwoDsq, boolean next) {
		this.league = league;
		this.round = round;
		this.set = set;
		this.raceNo = raceNo;
		this.division = division;
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.winner = winner;
		this.teamOneDsq = teamOneDsq;
		this.teamTwoDsq = teamTwoDsq;
		this.next = next;
	}
	
	/**
	 * Convenience constructor which clones another instance
	 * 
	 * @param raceToCopy The {@link Race} to be cloned.
	 */
	public Race(Race raceToCopy) {
		this.league = raceToCopy.getLeague();
		this.round = raceToCopy.getRound();
		this.set = raceToCopy.getSet();
		this.raceNo = raceToCopy.getRaceNo();
		this.division = raceToCopy.getDivision();
		this.teamOne = raceToCopy.getTeamOne();
		this.teamTwo = raceToCopy.getTeamTwo();
		this.winner = raceToCopy.getWinner();
		this.teamOneDsq = raceToCopy.getTeamOneDsq();
		this.teamTwoDsq = raceToCopy.getTeamTwoDsq();
		this.next = raceToCopy.isNext();
	}

	/**
	 * @return the league
	 */
	public String getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(String league) {
		this.league = league;
	}

	/**
	 * @return the round
	 */
	public String getRound() {
		return round;
	}

	/**
	 * @param round the round to set
	 */
	public void setRound(String round) {
		this.round = round;
	}

	/**
	 * @return the set
	 */
	public String getSet() {
		return set;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(String set) {
		this.set = set;
	}

	/**
	 * @return the raceNo
	 */
	public int getRaceNo() {
		return raceNo;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @return the teamOne
	 */
	public String getTeamOne() {
		return teamOne;
	}

	/**
	 * @return the teamTwo
	 */
	public String getTeamTwo() {
		return teamTwo;
	}

	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * set the teamTwoDsq
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * @return the teamOneDsq
	 */
	public String getTeamOneDsq() {
		return teamOneDsq;
	}

	/**
	 * set the teamTwoDsq
	 */
	public void setTeamOneDsq(String teamOneDsq) {
		this.teamOneDsq = teamOneDsq;
	}

	/**
	 * @return the teamTwoDsq
	 */
	public String getTeamTwoDsq() {
		return teamTwoDsq;
	}

	/**
	 * set the teamTwoDsq
	 */
	public void setTeamTwoDsq(String teamTwoDsq) {
		this.teamTwoDsq = teamTwoDsq;
	}

	/**
	 * @return the next
	 */
	public boolean isNext() {
		return next;
	}

	/**
	 * Set the next
	 */
	public void setNext(boolean next) {
		this.next = next;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (Race.class.equals(obj.getClass())) {
			Race other = (Race)obj;
			
			if (this.league == null || this.round == null || this.set == null ||
					this.division == null || this.teamOne == null || this.teamTwo == null) {
				return false;
			}
			
			return this.league.equals(other.getLeague()) &&
					this.round.equals(other.getRound()) &&
					this.set.equals(other.getSet()) &&
					this.division.equals(other.getDivision()) &&
					this.raceNo == other.raceNo &&
					this.teamOne.equals(other.teamOne) &&
					this.teamTwo.equals(other.teamTwo);
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;

        result = prime * result + (league == null ? 0 : league.hashCode());
        result = prime * result + (round == null ? 0 : round.hashCode());
        result = prime * result + (set == null ? 0 : set.hashCode());
        result = prime * result + (division == null ? 0 : division.hashCode());
        result = prime * result + raceNo;
        result = prime * result + (teamOne == null ? 0 : teamOne.hashCode());
        result = prime * result + (teamTwo == null ? 0 : teamTwo.hashCode());

		return result;
	}
}
