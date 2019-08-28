package org.kingsski.kaas.database.jdbc;

import org.junit.runner.RunWith;
import org.kingsski.kaas.database.club.ClubDao;
import org.kingsski.kaas.database.factory.JdbcDaoFactory;
import org.kingsski.kaas.database.team.TeamDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final String TRUNCATE_CASCADE_CLUB = "TRUNCATE t_club CASCADE";
    private static final String TRUNCATE_CASCADE_ORGANISATION = "TRUNCATE t_organisation CASCADE";

    private static final String ADD_ORGANISATION = "INSERT INTO t_organisation( name ) VALUES ( ? )";
    private static final String ADD_COMPETITION = "INSERT INTO t_competition( organisation_id, name ) VALUES" +
            "( (SELECT organisation_id FROM t_organisation WHERE name = ?), ?)";
    private static final String ADD_SEASON = "INSERT INTO t_season( competition_id, name ) " +
            "VALUES ( (SELECT competition_id FROM t_competition WHERE name = ?), ?)";

    @Resource
    private JdbcDaoFactory daoFactory;

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @Resource
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Clear the database by truncating all tables
     */
    protected void clearDb() {
        jdbcTemplate.execute(TRUNCATE_CASCADE_CLUB);
        jdbcTemplate.execute(TRUNCATE_CASCADE_ORGANISATION);
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
        ClubDao clubDao = daoFactory.newClubDao();
        clubDao.addClub(club);

        TeamDao teamDao = daoFactory.newTeamDao();
        // add teams
        for (int i = 1; i <= teamCount; i++) {
            final String team = club + " " + i;
            teamDao.addTeam(team, club);
        }
    }

    protected Map<String, List<String>> addOrganisation(String organisation) {
        return addOrganisation(organisation, 0);
    }

    protected Map<String, List<String>> addOrganisation(String organisation, int competitionCount) {
        return addOrganisation(organisation, competitionCount, 0);
    }

    /**
     * Add an organisation to the database and some number of competitions for it. If an
     * organisation is called "KINGS" and 2 competitions are required with 3 seasons then
     * the below entities will be created
     * <ul>
     *     <li>t_organisation: "KINGS"</li>
     *     <li>t_competition: "KINGS competition1"</li>
     *     <li>t_season: "KINGS competition1 season1"</li>
     *     <li>t_season: "KINGS competition1 season2"</li>
     *     <li>t_season: "KINGS competition1 season3"</li>
     *     <li>t_competition: "KINGS competition2"</li>
     *     <li>etc.</li>
     * </ul>
     *
     * @param organisation the name of the organisation to create
     * @param competitionCount the number of competitions to create for the organisation
     * @param seasonCount the number of seasons to create for each competition organisation
     */
    protected Map<String, List<String>> addOrganisation(String organisation, int competitionCount, int seasonCount) {
        // add organisation
        jdbcTemplate.update(ADD_ORGANISATION, organisation);

        Map<String, List<String>> createdCompetitions = new HashMap<>();

        // add competitions
        for (int i = 1; i <= competitionCount; i++) {
            final String competition = organisation + " competition" + i;
            jdbcTemplate.update(ADD_COMPETITION,
                    organisation, competition
            );

            List<String> createdSeasons = new ArrayList<>();
            for (int j = 1; j <= seasonCount; j++) {
                final String season = competition + " season" + j;
                jdbcTemplate.update(ADD_SEASON,
                        competition, season
                );
                createdSeasons.add(season);
            }
            createdCompetitions.put(competition, createdSeasons);
        }

        return createdCompetitions;
    }
}
