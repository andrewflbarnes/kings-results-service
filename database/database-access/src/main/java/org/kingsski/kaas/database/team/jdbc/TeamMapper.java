package org.kingsski.kaas.database.team.jdbc;

import org.kingsski.kaas.database.team.Team;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Maps service results to {@link Team}s. Expected fields are
 * <ol>
 *     <li>team_id (long)</li>
 *     <li>name (string)</li>
 *     <li>club (string)</li>
 * </ol>
 */
public class TeamMapper implements RowMapper<Team> {

    @Nullable
    @Override
    public Team mapRow(ResultSet resultSet, int i) throws SQLException {
        return Team.builder()
                .id(resultSet.getLong("team_id"))
                .name(resultSet.getString("name"))
                .club(resultSet.getString("club"))
                .build();
    }
}
