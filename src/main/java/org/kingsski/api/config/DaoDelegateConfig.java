package org.kingsski.api.config;

import org.kingsski.api.dao.individual.IndividualDao;
import org.kingsski.api.dao.race.RaceDao;
import org.kingsski.api.dao.team.TeamDao;

public interface DaoDelegateConfig {

    TeamDao teamDao();

    RaceDao raceDao();

    IndividualDao individualDao();
}
