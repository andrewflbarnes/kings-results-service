package org.kingsski.kaas.database.division.jdbc;

import org.kingsski.kaas.database.division.Division;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link Division}s. Expected fields are
 * <ol>
 *     <li>id (long)</li>
 *     <li>name (string)</li>
 *     <li>organisation (string)</li>
 *     <li>competition (string)</li>
 * </ol>
 */
public class DivisionMapper implements RowMapper<Division> {

    @Nullable
    @Override
    public Division mapRow(ResultSet resultSet, int i) throws SQLException {
        return Division.builder()
                .id(resultSet.getLong("division_id"))
                .name(resultSet.getString("division"))
                .organisation(resultSet.getString("organisation"))
                .competition(resultSet.getString("competition"))
                .build();
    }
}
