package org.kingsski.api.config;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;

public interface DaoConfig {

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
