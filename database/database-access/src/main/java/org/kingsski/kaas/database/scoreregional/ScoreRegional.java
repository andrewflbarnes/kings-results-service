package org.kingsski.kaas.database.scoreregional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Model reflecting the SCORE_REGIONAL view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRegional {

    private static final String TO_STRING = "ScoreRegional[" +
            "club:%s,team:%s,organisation:%s,competition:%s,season:%s," +
            "league:%s,regional:%s,division:%s,position:%d,score:%d]";

    private String club;
    private String team;
    private String organisation;
    private String competition;
    private String season;
    private String league;
    private String regional;
    private String division;
    private int position;
    private int score;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(team)
                .append(club)
                .append(organisation)
                .append(competition)
                .append(season)
                .append(league)
                .append(regional)
                .append(division)
                .append(position)
                .append(score)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ScoreRegional)) {
            return false;
        }

        ScoreRegional other = (ScoreRegional)obj;

        return new EqualsBuilder()
                .append(this.team, other.team)
                .append(this.club, other.club)
                .append(this.organisation, other.organisation)
                .append(this.competition, other.competition)
                .append(this.season, other.season)
                .append(this.league, other.league)
                .append(this.regional, other.regional)
                .append(this.division, other.division)
                .append(this.position, other.position)
                .append(this.score, other.score)
                .isEquals();
    }

    @Override
    public String toString() {
        return String.format(TO_STRING,
                team, club, organisation, competition, season,
                league, regional, division, position, score);
    }
}
