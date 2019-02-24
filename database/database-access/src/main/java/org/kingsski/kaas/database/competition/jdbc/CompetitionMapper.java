package org.kingsski.kaas.database.competition.jdbc;

import org.kingsski.kaas.database.competition.Competition;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link Competition}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>organisation (string)</li>
 * </ol>
 */
public class CompetitionMapper implements RowMapper<Competition> {

    @Nullable
    @Override
    public Competition mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Competition()
                .id(resultSet.getLong("competition_id"))
                .name(resultSet.getString("name"))
                .organisation(resultSet.getString("organisation"));
    }
}
