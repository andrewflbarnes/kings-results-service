package org.kingsski.kaas.database.league.jdbc;

import org.kingsski.kaas.database.league.League;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link League}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>organisation (string)</li>
 *     <li>competition (string)</li>
 * </ol>
 */
public class LeagueMapper implements RowMapper<League> {

    @Nullable
    @Override
    public League mapRow(ResultSet resultSet, int i) throws SQLException {
        return League.builder()
                .id(resultSet.getLong("league_id"))
                .name(resultSet.getString("league"))
                .organisation(resultSet.getString("organisation"))
                .comnpetition(resultSet.getString("competition"))
                .build();
    }
}
