package org.kingsski.api.dao;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;

public interface DaoFactory {

    String getDbProfile();

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
