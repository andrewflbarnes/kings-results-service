package org.kingsski.data.dao.jdbc.mapper;

import org.kingsski.data.model.Individual;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndividualMapper implements RowMapper<Individual> {
    @Nullable
    @Override
    public Individual mapRow(ResultSet resultSet, int i) throws SQLException {
        Individual indi = new Individual();

        indi.setId(resultSet.getLong(1));
        indi.setName(resultSet.getString(2));
        indi.setDiscipline(resultSet.getString(3));
        indi.setClub(resultSet.getString(4));
        indi.setPosition(resultSet.getInt(5));
        indi.setTime1(resultSet.getInt(6));
        indi.setTime2(resultSet.getInt(7));
        indi.setTotalTime(resultSet.getInt(8));

        return indi;
    }
}
