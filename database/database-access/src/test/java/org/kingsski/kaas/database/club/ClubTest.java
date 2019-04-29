package org.kingsski.kaas.database.club;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class ClubTest {

    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "nameValue";
    private static final int DEFAULT_TEAM_COUNT = 2;

    private Club.ClubBuilder resetBuilder(Club.ClubBuilder builder) {
        builder.id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .teamCount(DEFAULT_TEAM_COUNT);

        return builder;
    }

    @Test
    public void equalityTests() {
        Club.ClubBuilder builder = Club.builder();
        resetBuilder(builder);

        Club defaultClub = builder.build();

        assertEqualsStrong(
                defaultClub,
                builder.build(),
                resetBuilder(builder).teamCount(9999).build()
        );

        assertFalse(defaultClub.equals(null));
        assertNotEqualsStrong(
                defaultClub,
                resetBuilder(builder).id(9999).build(),
                resetBuilder(builder).name("boom").build(),
                resetBuilder(builder).name(null).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        Club.ClubBuilder builder = Club.builder();
        Club club = resetBuilder(builder).build();
        String clubString = club.toString();
        assertTrue(clubString.contains(DEFAULT_NAME));
        assertTrue(clubString.contains(String.valueOf(DEFAULT_ID)));
        assertTrue(clubString.contains(String.valueOf(DEFAULT_TEAM_COUNT)));
    }

}
