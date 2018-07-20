package org.kingsski.api.test.config;

import org.kingsski.api.config.dao.JdbcDaoConfig;
import org.kingsski.api.dao.jdbc.JdbcIndividualDao;
import org.kingsski.api.dao.jdbc.JdbcRaceDao;
import org.kingsski.api.dao.jdbc.JdbcTeamDao;
import org.kingsski.api.model.Individual;
import org.kingsski.api.model.Race;
import org.kingsski.api.model.Team;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestConfiguration
@ImportResource("classpath:jdbc-config.xml")
@Import(JdbcDaoConfig.class)
public class TestJdbcConfig {

    @Resource(name = "jdbcShared")
    private Map<String, String> jdbcSharedStatements;

    private List<Team> teams = new ArrayList<>();

    private List<Individual> individuals = new ArrayList<>();

    private List<Race> races = new ArrayList<>();

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate mockTemplate = Mockito.mock(JdbcTemplate.class);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcTeamDao.SELECT_ALL)),
                any(Team.TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcTeamDao.SELECT_BY_LEAGUE)),
                any(Object[].class),
                any(Team.TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcTeamDao.SELECT_BY_LEAGUE_DIVISION)),
                any(Object[].class),
                any(Team.TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_ALL)),
                any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_BY_CLUB)),
                any(Object[].class),
                any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_BY_DISCIPLINE)),
                any(Object[].class),
                any(Individual.IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_ALL)),
                any(Race.RaceMapper.class)))
                .thenReturn(races);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_BY_LEAGUE)),
                any(Object[].class),
                any(Race.RaceMapper.class)))
                .thenReturn(races);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_BY_LEAGUE_ROUND)),
                any(Object[].class),
                any(Race.RaceMapper.class)))
                .thenReturn(races);

        return mockTemplate;
    }
}
