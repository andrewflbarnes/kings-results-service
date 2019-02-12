package org.kingsski.database.organisation.jdbc;

import org.kingsski.database.organisation.Organisation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps query results to {@link Organisation}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>number of competitions (long)</li>
 * </ol>
 */
public class OrganisationMapper implements RowMapper<Organisation> {

    @Nullable
    @Override
    public Organisation mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Organisation()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .competitionCount(resultSet.getLong(3));
    }
}
