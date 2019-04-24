package org.kingsski.kaas.database.scoreregional.jdbc;

import org.kingsski.kaas.database.competition.Competition;
import org.kingsski.kaas.database.scoreregional.ScoreRegional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link Competition}s. Expected fields are
 * <ol>
 *     <li>club (string)</li>
 *     <li>team (string)</li>
 *     <li>organisation (string)</li>
 *     <li>competition (string)</li>
 *     <li>season (string)</li>
 *     <li>league (string)</li>
 *     <li>regional (string)</li>
 *     <li>division (string)</li>
 *     <li>position (int)</li>
 *     <li>score (string)</li>
 * </ol>
 */
public class ScoreRegionalMapper implements RowMapper<ScoreRegional> {

    @Nullable
    @Override
    public ScoreRegional mapRow(ResultSet resultSet, int i) throws SQLException {
        return ScoreRegional.builder()
                .club(resultSet.getString("club"))
                .team(resultSet.getString("team"))
                .organisation(resultSet.getString("organisation"))
                .competition(resultSet.getString("competition"))
                .season(resultSet.getString("season"))
                .league(resultSet.getString("league"))
                .regional(resultSet.getString("regional"))
                .division(resultSet.getString("division"))
                .position(resultSet.getInt("position"))
                .score(resultSet.getInt("score"))
                .build();
    }
}
