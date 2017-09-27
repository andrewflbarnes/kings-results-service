package kingsraceservice.model;

/**
 * @author Barnesly
 *
 */
public class DummyRace {
	
	private int raceNo;
	private String division;
	private String teamOne;
	private String teamTwo;
	private String winner;
	private String teamOneDsq;
	private String teamTwoDsq;
	private boolean next;

	/**
	 * @param raceNo
	 * @param division
	 * @param teamOne
	 * @param teamTwo
	 * @param winner
	 * @param teamOneDsq
	 * @param teamTwoDsq
	 */
	public DummyRace(int raceNo, String division, String teamOne, String teamTwo, String winner, String teamOneDsq,
			String teamTwoDsq, boolean next) {
		this.raceNo = raceNo;
		this.division = division;
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.winner = winner;
		this.teamOneDsq = teamOneDsq;
		this.teamTwoDsq = teamTwoDsq;
		this.next = next;
	}
	
	public DummyRace(DummyRace raceToCopy) {
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

}
