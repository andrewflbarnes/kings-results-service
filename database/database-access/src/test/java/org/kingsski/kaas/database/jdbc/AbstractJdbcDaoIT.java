package org.kingsski.kaas.database.jdbc;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Abstract class for JDBC ITs which automatically pulls in a reference to
 * a {@link JdbcTemplate} and provides convenience methods for
 * <ul>
 *     <li>Clearing the database</li>
 *     <li>Adding entities to tables</li>
 * </ul>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = JdbcIntegrationTestConfig.class)
public abstract class AbstractJdbcDaoIT {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    /**
     * Clear the database by truncating all tables
     */
    protected void clearDb() {
        jdbcTemplate.execute("TRUNCATE t_club CASCADE");
        jdbcTemplate.execute("TRUNCATE t_organisation CASCADE");
    }

    /**
     * Add a club to the database and some number of teams for it. If a club is
     * called "SKUM" and 4 teams are required then the below entities will be created
     * <ul>
     *     <li>t_club: "SKUM"</li>
     *     <li>t_team: "SKUM 1"</li>
     *     <li>t_team: "SKUM 2"</li>
     *     <li>t_team: "SKUM 3"</li>
     *     <li>t_team: "SKUM 4"</li>
     * </ul>
     *
     * @param club the name of the club to create
     * @param teamCount the number of teams to create for the club
     */
    protected void addClubAndTeams(String club, int teamCount) {
        // add club
        jdbcTemplate.update("INSERT INTO t_club( name ) VALUES ( ? )", club);

        // add teams
        for (int i = 1; i <= teamCount; i++) {
            final String team = club + " " + i;
            jdbcTemplate.update("INSERT INTO t_team( club_id, name ) " +
                            "VALUES ( (SELECT club_id FROM t_club WHERE name = ?), ?)",
                    club, team
            );
        }
    }

    /**
     * Add an organisation to the database and some number of competitions for it. If an
     * organisation is called "KINGS" and 4 competitions are required then the below entities
     * will be created
     * <ul>
     *     <li>t_organisation: "KINGS"</li>
     *     <li>t_competition: "KINGS competition1"</li>
     *     <li>t_competition: "KINGS competition2"</li>
     *     <li>t_competition: "KINGS competition3"</li>
     *     <li>t_competition: "KINGS competition4"</li>
     * </ul>
     *
     * @param organisation the name of the organisation to create
     * @param competitionCount the number of competitions to create for the organisation
     */
    protected void addOrganisationAndCompetitions(String organisation, int competitionCount) {
        // add organisation
        jdbcTemplate.update("INSERT INTO t_organisation( name ) VALUES ( ? )", organisation);

        // add competitions
        for (int i = 1; i <= competitionCount; i++) {
            final String competition = organisation + " competition" + i;
            jdbcTemplate.update("INSERT INTO t_competition( organisation_id, name ) " +
                            "VALUES ( (SELECT organisation_id FROM t_organisation WHERE name = ?), ?)",
                    organisation, competition
            );
        }
    }
}
