package org.kingsski.api.dao.delegate;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;

// Could extend DaoFactory but then multiple beans fulfil criteria...
public interface DaoDelegateFactory {

    String getDbProfile();

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
