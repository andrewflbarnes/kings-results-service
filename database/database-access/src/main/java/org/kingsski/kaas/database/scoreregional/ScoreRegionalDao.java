package org.kingsski.kaas.database.scoreregional;

import java.util.List;

/**
 * Defines the DAO operations for the SCORE_REGIONAL view (querying)
 */
public interface ScoreRegionalDao {

    /**
     * Get a list of all regional scores
     * @return a list of regional scores
     */
    List<ScoreRegional> getScoreRegionals();
}
