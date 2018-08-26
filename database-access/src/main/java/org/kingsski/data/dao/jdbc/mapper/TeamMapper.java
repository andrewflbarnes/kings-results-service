package org.kingsski.data.dao.jdbc.mapper;

import org.kingsski.data.model.Team;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper implements RowMapper<Team> {

    @Nullable
    @Override
    public Team mapRow(ResultSet resultSet, int i) throws SQLException {
        Team team = new Team();
        team.setId(resultSet.getInt(1));
        team.setTeamName(resultSet.getString(2));
        team.setDivision(resultSet.getString(3));
        team.setLeague(resultSet.getString(4));
        team.setPosition(resultSet.getInt(5));
        team.setR1(resultSet.getInt(6));
        team.setR2(resultSet.getInt(7));
        team.setR3(resultSet.getInt(8));
        team.setR4(resultSet.getInt(9));
        // TODO emit warning when total is incorrect
        return team;
    }
}

