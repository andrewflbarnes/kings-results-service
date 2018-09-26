package org.kingsski.database.dao.jdbc;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = IntegrationTestConfig.class)
public abstract class AbstractDaoIT {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    protected void clearDb() {
        jdbcTemplate.execute("TRUNCATE t_club CASCADE");
        jdbcTemplate.execute("TRUNCATE T_organisation CASCADE");
    }

    protected void addClubAndTeams(String club, int teamCount) {
        // add club
        jdbcTemplate.update("INSERT INTO t_club( name ) VALUES ( '" + club + "' )");

        // add teams
        for (int i = 1; i <= teamCount; i++) {
            final String team = club + " " + i;
            jdbcTemplate.update("INSERT INTO t_team( club_id, name ) " +
                            "VALUES ( (SELECT club_id FROM t_club WHERE name = ?), ?)",
                    club, team
            );
        }
    }
}