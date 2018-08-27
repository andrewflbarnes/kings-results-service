package org.kingsski.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Team {

    private long id;
    private String clubName;
    private String teamName;
    private String organisation;
    private String competition;
    private String season;
    private String league;
    private String division;
    private int position;
    private List<RegionalScore> scores = new ArrayList<>();
    private int total;
    private RegionalScore[] orderedScores;

    public Team() {
        // default constructor
    }

    public Team(String teamName, String league, String division, int position, List<RegionalScore> scores) {
        this.teamName = teamName;
        this.league = league;
        this.division = division;
        this.position = position;
        setScores(scores);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns a deep copy of the {@link RegionalScore}s
     *
     * @return the {@link RegionalScore}s for this team
     */
    public List<RegionalScore> getScores() {
        List<RegionalScore> deepCopyReturn = new ArrayList<>(scores.size());
        for (RegionalScore rScore : this.scores) {
            RegionalScore deepCopy = new RegionalScore();
            deepCopy.setName(rScore.getName());
            deepCopy.setScore(rScore.getScore());
            deepCopyReturn.add(deepCopy);
        }

        return deepCopyReturn;
    }

    /**
     * Sets a deep copy of the {@link RegionalScore}s for this team
     */
    public void setScores(List<RegionalScore> scores) {
        this.scores = new ArrayList<>(scores.size());
        for (RegionalScore rScore : scores) {
            RegionalScore deepCopy = new RegionalScore();
            deepCopy.setName(rScore.getName());
            deepCopy.setScore(rScore.getScore());
            this.scores.add(deepCopy);
        }

        updateScores();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Get the score for the specified regional
     *
     * @param regional the name of the regional competition
     * @return the score if it exists, 0 otherwise
     */
    public int getScore(final String regional) {
        return scores
                .stream()
                .filter(s -> regional.equalsIgnoreCase(s.getName()))
                .findFirst()
                .orElseGet(RegionalScore::new)
                .getScore();
    }

    /**
     * Get the score for the specified regional number (assumes correct ordering!)
     *
     * @param ordinal the number of the regional competition
     * @return the score if it exists, 0 otherwise
     */
    public int getScore(final int ordinal) {
        RegionalScore regionalScore = scores.get(ordinal);
        return regionalScore == null ? 0 : regionalScore.getScore();
    }

    /**
     * Sets the score for a regional round
     *
     * @param regional The name of the regional competition
     * @param score The score to set
     */
    public void setScore(final String regional, final int score) {
        scores
                .stream()
                .filter(s -> regional.equalsIgnoreCase(s.getName()))
                .findFirst()
                .orElseGet(() -> {
                    RegionalScore rScore = new RegionalScore();
                    rScore.setName(regional);
                    scores.add(rScore);
                    return rScore;
                })
                .setScore(score);
        this.updateScores();
    }

    public int getOrderedScore(int ordinal) {
        if (this.scores.size() <= ordinal) {
            return 0;
        }

        return this.orderedScores[ordinal].getScore();
    }

    public void updateScores() {
        this.orderedScores = this.scores.toArray(new RegionalScore[this.scores.size()]);

        this.total = 0;

        for (int i = 0; i < orderedScores.length - 1; ++i) {
            for (int j = i + 1; j < orderedScores.length; ++j) {
                if (orderedScores[i].getScore() < orderedScores[j].getScore()) {
                    RegionalScore tempRScore = orderedScores[i];
                    orderedScores[i] = orderedScores[j];
                    orderedScores[j] = tempRScore;
                }
            }
        }

        for (RegionalScore rScore : this.scores) {
            this.total += rScore.getScore();
        }

        this.scores = this.scores.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
