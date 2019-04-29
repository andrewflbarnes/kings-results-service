package org.kingsski.kaas.database.season.jdbc;

import org.kingsski.kaas.database.season.Season;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link Season}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>organisation (string)</li>
 *     <li>competition (string)</li>
 * </ol>
 */
public class SeasonMapper implements RowMapper<Season> {

    @Nullable
    @Override
    public Season mapRow(ResultSet resultSet, int i) throws SQLException {
        return Season.builder()
                .id(resultSet.getLong("season_id"))
                .name(resultSet.getString("season"))
                .organisation(resultSet.getString("organisation"))
                .comnpetition(resultSet.getString("competition"))
                .build();
    }
}
