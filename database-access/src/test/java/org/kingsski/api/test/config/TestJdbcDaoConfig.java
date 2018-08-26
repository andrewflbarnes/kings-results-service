package org.kingsski.api.test.config;

import org.kingsski.data.dao.DaoFactory;
import org.kingsski.data.dao.jdbc.JdbcDaoFactory;
import org.kingsski.data.dao.jdbc.JdbcIndividualDao;
import org.kingsski.data.dao.jdbc.JdbcRaceDao;
import org.kingsski.data.dao.jdbc.JdbcTeamDao;
import org.kingsski.data.dao.jdbc.mapper.IndividualMapper;
import org.kingsski.data.dao.jdbc.mapper.RaceMapper;
import org.kingsski.data.dao.jdbc.mapper.TeamMapper;
import org.kingsski.data.model.Individual;
import org.kingsski.data.model.Race;
import org.kingsski.data.model.Team;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ImportResource("classpath:jdbc-config.xml")
public class TestJdbcDaoConfig {

    @Bean("delegate")
    public DaoFactory daoDelegateConfig(JdbcTemplate jdbcTemplate) {
        return new JdbcDaoFactory(jdbcTemplate, jdbcSharedStatements);
    }

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
                any(TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcTeamDao.SELECT_BY_LEAGUE)),
                any(Object[].class),
                any(TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcTeamDao.SELECT_BY_LEAGUE_DIVISION)),
                any(Object[].class),
                any(TeamMapper.class)))
                .thenReturn(teams);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_ALL)),
                any(IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_BY_CLUB)),
                any(Object[].class),
                any(IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcIndividualDao.SELECT_BY_DISCIPLINE)),
                any(Object[].class),
                any(IndividualMapper.class)))
                .thenReturn(individuals);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_ALL)),
                any(RaceMapper.class)))
                .thenReturn(races);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_BY_LEAGUE)),
                any(Object[].class),
                any(RaceMapper.class)))
                .thenReturn(races);

        when(mockTemplate.query(
                eq(jdbcSharedStatements.get(JdbcRaceDao.SELECT_BY_LEAGUE_ROUND)),
                any(Object[].class),
                any(RaceMapper.class)))
                .thenReturn(races);

        return mockTemplate;
    }
}
