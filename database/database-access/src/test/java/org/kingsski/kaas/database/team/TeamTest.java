package org.kingsski.kaas.database.team;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class TeamTest {

    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "nameValue";
    private static final String DEFAULT_CLUB = "clubValue";

    private Team.TeamBuilder resetBuilder(Team.TeamBuilder builder) {
        builder.id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .club(DEFAULT_CLUB);

        return builder;
    }

    @Test
    public void equalityTests() {
        Team.TeamBuilder builder = Team.builder();
        resetBuilder(builder);

        Team defaultTeam = builder.build();

        assertEqualsStrong(
                defaultTeam,
                builder.build()
        );

        assertFalse(defaultTeam.equals(null));
        assertNotEqualsStrong(
                defaultTeam,
                resetBuilder(builder).id(9999).build(),
                resetBuilder(builder).name("boom").build(),
                resetBuilder(builder).name(null).build(),
                resetBuilder(builder).club("boom").build(),
                resetBuilder(builder).club(null).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        final String teamName = "aeuogewgow";
        final String clubName = "vve5ujddth";
        final long id = 341213L;

        Team team = Team.builder().name(teamName).id(id).club(clubName).build();
        String teamString = team.toString();
        assertTrue(teamString.contains(teamName));
        assertTrue(teamString.contains(String.valueOf(id)));
        assertTrue(teamString.contains(clubName));
    }

}
