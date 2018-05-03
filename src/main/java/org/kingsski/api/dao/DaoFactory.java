package org.kingsski.api.dao;

public interface DaoFactory {

    String getDbProfile();

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
