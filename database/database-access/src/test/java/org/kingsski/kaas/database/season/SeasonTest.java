package org.kingsski.kaas.database.season;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class SeasonTest {

    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "nameValue";
    private static final String DEFAULT_ORGANISIATION = "organisationValue";
    private static final String DEFAULT_COMPETITION = "competitionValue";

    private Season.SeasonBuilder resetBuilder(Season.SeasonBuilder builder) {
        builder.id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .competition(DEFAULT_COMPETITION)
                .organisation(DEFAULT_ORGANISIATION);

        return builder;
    }

    @Test
    public void equalityTests() {
        Season.SeasonBuilder builder = Season.builder();

        Season defaultSeason = resetBuilder(builder).build();

        assertFalse(defaultSeason.equals(null));

        assertEqualsStrong(
                defaultSeason,
                builder.build()
        );

        assertNotEqualsStrong(
                defaultSeason,
                resetBuilder(builder).id(2).build(),
                resetBuilder(builder).name("boom").build(),
                resetBuilder(builder).organisation("boom").build(),
                resetBuilder(builder).competition("boom").build(),
                resetBuilder(builder).name(null).build(),
                resetBuilder(builder).organisation(null).build(),
                resetBuilder(builder).competition(null).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        Season season = resetBuilder(Season.builder()).build();
        String seasonString = season.toString();
        assertTrue(seasonString.contains(DEFAULT_NAME));
        assertTrue(seasonString.contains(DEFAULT_ORGANISIATION));
        assertTrue(seasonString.contains(DEFAULT_COMPETITION));
        assertTrue(seasonString.contains(String.valueOf(DEFAULT_ID)));
    }

}
