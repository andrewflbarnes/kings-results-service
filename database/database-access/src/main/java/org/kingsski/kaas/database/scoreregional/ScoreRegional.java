package org.kingsski.kaas.database.scoreregional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Model reflecting the SCORE_REGIONAL view in the database
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"position", "score"})
public class ScoreRegional {
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
}
