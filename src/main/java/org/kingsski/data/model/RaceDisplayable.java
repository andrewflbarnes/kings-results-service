package org.kingsski.data.model;

import org.kingsski.wax.data.Race;

import javax.validation.constraints.NotNull;

/**
 * This class represents a single displayable Kings race.
 * <p>
 * This class is designed as a DTO/VO only and is not
 * appropriate for processing actual races!
 */
public class RaceDisplayable extends Race {

    private String roundName;
    private String teamOneName;
    private String teamTwoName;
    private String winnerName;

    /**
     * Standard constructor for a new {@link RaceDisplayable}
     *
     * @param raceNo      The number of this race in the current roundName
     * @param division    The division this race belongs to
     * @param teamOneName The first team competing
     * @param teamTwoName The second team competing
     * @param winnerName  The winnerName of the race
     * @param teamOneDsq  The reason the first team was disqualified
     * @param teamTwoDsq  The reason the second team was disqualified
     * @param next        true if this is the next race being run, false otherwise
     */
    public RaceDisplayable(@NotNull String league, @NotNull String roundName, @NotNull String set, int raceNo,
                           @NotNull String division, @NotNull String teamOneName, @NotNull String teamTwoName,
                           String winnerName, String teamOneDsq, String teamTwoDsq, boolean next) {
        setLeague(league);
        setRaceNo(raceNo);
        setDivision(division);
        setSet(set);
        setTeamOneDsq(teamOneDsq);
        setTeamTwoDsq(teamTwoDsq);
        setNext(next);

        this.roundName = roundName;
        this.teamOneName = teamOneName;
        this.teamTwoName = teamTwoName;
        this.winnerName = winnerName;
    }

    /**
     * Convenience constructor which clones another instance
     *
     * @param raceToCopy The {@link RaceDisplayable} to be cloned.
     */
    public RaceDisplayable(RaceDisplayable raceToCopy) {
        setLeague(raceToCopy.getLeague());
        setRaceNo(raceToCopy.getRaceNo());
        setDivision(raceToCopy.getDivision());
        setSet(raceToCopy.getSet());
        setTeamOneDsq(raceToCopy.getTeamOneDsq());
        setTeamTwoDsq(raceToCopy.getTeamTwoDsq());
        setNext(raceToCopy.isNext());

        roundName = raceToCopy.getRoundName();
        teamOneName = raceToCopy.getTeamOneName();
        teamTwoName = raceToCopy.getTeamTwoName();
        winnerName = raceToCopy.getWinnerName();
    }

    /**
     * @return the roundName
     */
    public String getRoundName() {
        return roundName;
    }

    /**
     * @return the teamOneName
     */
    public String getTeamOneName() {
        return teamOneName;
    }

    /**
     * @return the teamTwoName
     */
    public String getTeamTwoName() {
        return teamTwoName;
    }

    /**
     * @return the winnerName
     */
    public String getWinnerName() {
        return winnerName;
    }

    /**
     * set the teamTwoDsq
     */
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (RaceDisplayable.class.equals(obj.getClass())) {
                RaceDisplayable other = (RaceDisplayable) obj;

                if (this.roundName == null || this.teamOneName == null || this.teamTwoName == null) {
                    return false;
                }

                return this.roundName.equals(other.getRoundName()) &&
                        this.teamOneName.equals(other.teamOneName) &&
                        this.teamTwoName.equals(other.teamTwoName);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;

        int result = super.hashCode();
        result = prime * result + (roundName == null ? 0 : roundName.hashCode());
        result = prime * result + (teamOneName == null ? 0 : teamOneName.hashCode());
        result = prime * result + (teamTwoName == null ? 0 : teamTwoName.hashCode());

        return result;
    }
}
