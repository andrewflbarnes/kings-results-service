package org.kingsski.database.club.jdbc;

import org.kingsski.database.club.Club;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps query results to {@link Club}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>number of teams (long)</li>
 * </ol>
 */
public class ClubMapper implements RowMapper<Club> {

    @Nullable
    @Override
    public Club mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Club()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .teamCount(resultSet.getLong(3));
    }
}
