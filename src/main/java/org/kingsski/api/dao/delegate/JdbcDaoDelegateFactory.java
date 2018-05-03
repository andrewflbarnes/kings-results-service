package org.kingsski.api.dao.delegate;

import org.kingsski.api.dao.IndividualDao;
import org.kingsski.api.dao.RaceDao;
import org.kingsski.api.dao.TeamDao;
import org.kingsski.api.dao.jdbc.JdbcTeamDao;
import org.kingsski.api.dao.stub.StubIndividualDao;
import org.kingsski.api.dao.stub.StubRaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("jdbc")
public class JdbcDaoDelegateFactory implements DaoDelegateFactory {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getDbProfile() {
        return "jdbc";
    }

    @Override
    public TeamDao teamDao() {
        return new JdbcTeamDao(jdbcTemplate);
    }

    @Override
    public RaceDao raceDao() {
        return new StubRaceDao();
    }

    @Override
    public IndividualDao individualDao() {
        return new StubIndividualDao();
    }
}
