package org.kingsski.api.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Individual {

    private long id;
    private String name;
    private String discipline;
    private String club;
    private int position;
    private int time1;
    private int time2;
    private int totalTime;

    public Individual() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public static class IndividualMapper implements RowMapper<Individual> {
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
}
