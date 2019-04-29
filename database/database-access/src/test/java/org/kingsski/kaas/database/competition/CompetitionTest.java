package org.kingsski.kaas.database.competition;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class CompetitionTest {

    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "nameValue";
    private static final String DEFAULT_ORGANISATION = "organisationValue";

    private Competition.CompetitionBuilder resetBuilder(Competition.CompetitionBuilder builder) {
        builder.id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .organisation(DEFAULT_ORGANISATION);


        return builder;
    }

    @Test
    public void equalityTests() {
        Competition.CompetitionBuilder builder = Competition.builder();
        resetBuilder(builder);

        Competition defaultCompetition = builder.build();

        assertEqualsStrong(
                defaultCompetition,
                builder.build()

        );

        assertFalse(defaultCompetition.equals(null));
        assertNotEqualsStrong(
                defaultCompetition,
                resetBuilder(builder).id(9999).build(),
                resetBuilder(builder).name("boom").build(),
                resetBuilder(builder).organisation("boom").build(),
                resetBuilder(builder).name(null).build(),
                resetBuilder(builder).organisation(null).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        Competition competition = resetBuilder(Competition.builder()).build();

        String competitionString = competition.toString();
        assertTrue(competitionString.contains(DEFAULT_NAME));
        assertTrue(competitionString.contains(String.valueOf(DEFAULT_ID)));
        assertTrue(competitionString.contains(DEFAULT_ORGANISATION));
    }

}
