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
        team.setClubName(resultSet.getString(3));
        team.setOrganisation(resultSet.getString(4));
        team.setCompetition(resultSet.getString(5));
        team.setSeason(resultSet.getString(6));
        team.setLeague(resultSet.getString(7));
        team.setDivision(resultSet.getString(8));
        team.setScore(resultSet.getString(9), resultSet.getInt(10));
        return team;
    }
}

