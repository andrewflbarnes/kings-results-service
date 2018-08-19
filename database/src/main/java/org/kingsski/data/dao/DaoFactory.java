package org.kingsski.data.dao;

public interface DaoFactory {

    String getDbProfile();

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
