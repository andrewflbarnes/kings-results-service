package org.kingsski.data.dao.jdbc.mapper;

import org.kingsski.data.model.Race;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class represents a single displayable Kings race.
 * <p>
 * This class is designed as a DTO/VO only and is not
 * appropriate for processing actual races!
 */
public class RaceMapper implements RowMapper<Race> {

    @Nullable
    @Override
    public Race mapRow(ResultSet resultSet, int i) throws SQLException {
        Race race = new Race();
        race.setId(resultSet.getInt(1));
        race.setLeague(resultSet.getString(2));
        race.setRound(resultSet.getString(3));
        race.setSet(resultSet.getString(4));
        race.setRaceNo(resultSet.getInt(5));
        race.setDivision(resultSet.getString(6));
        race.setTeamOne(resultSet.getString(7));
        race.setTeamTwo(resultSet.getString(8));
        race.setWinner(resultSet.getString(9));
        race.setTeamOneDsq(resultSet.getString(10));
        race.setTeamTwoDsq(resultSet.getString(11));
        race.setNext(resultSet.getBoolean(12));

        return race;
    }
}
