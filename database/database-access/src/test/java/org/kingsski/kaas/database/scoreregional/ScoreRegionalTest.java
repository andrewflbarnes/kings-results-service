package org.kingsski.kaas.database.scoreregional;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class ScoreRegionalTest {

    private ScoreRegional.ScoreRegionalBuilder resetBuilder(ScoreRegional.ScoreRegionalBuilder builder) {
        builder.club("clubValue")
                .team("teamValue")
                .organisation("organisationValue")
                .competition("competitionValue")
                .league("leagueValue")
                .regional("regionalValue")
                .division("divisionValue")
                .season("seasonValue")
                .position(1)
                .score(20);

        return builder;
    }

    @Test
    public void equalityTests() {
        ScoreRegional.ScoreRegionalBuilder builder = ScoreRegional.builder();
        resetBuilder(builder);

        ScoreRegional defaultScoreRegional = builder.build();

        assertEqualsStrong(
                defaultScoreRegional,
                builder.build(),
                resetBuilder(builder).score(10).build(),
                resetBuilder(builder).position(2).build()
        );

        assertFalse(defaultScoreRegional.equals(null));
        assertNotEqualsStrong(
                defaultScoreRegional,
                resetBuilder(builder).organisation("boom").build(),
                resetBuilder(builder).organisation(null).build(),
                resetBuilder(builder).competition("boom").build(),
                resetBuilder(builder).competition(null).build(),
                resetBuilder(builder).season("boom").build(),
                resetBuilder(builder).season(null).build(),
                resetBuilder(builder).league("boom").build(),
                resetBuilder(builder).league(null).build(),
                resetBuilder(builder).regional("boom").build(),
                resetBuilder(builder).regional(null).build(),
                resetBuilder(builder).division("boom").build(),
                resetBuilder(builder).division(null).build(),
                resetBuilder(builder).club("boom").build(),
                resetBuilder(builder).club(null).build(),
                resetBuilder(builder).team("boom").build(),
                resetBuilder(builder).team(null).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        ScoreRegional.ScoreRegionalBuilder builder = ScoreRegional.builder();
        ScoreRegional scoreRegional = resetBuilder(builder).build();
        String scoreRegionalString = scoreRegional.toString();
        assertTrue(scoreRegionalString.contains("clubValue"));
        assertTrue(scoreRegionalString.contains("teamValue"));
        assertTrue(scoreRegionalString.contains("organisationValue"));
        assertTrue(scoreRegionalString.contains("competitionValue"));
        assertTrue(scoreRegionalString.contains("seasonValue"));
        assertTrue(scoreRegionalString.contains("leagueValue"));
        assertTrue(scoreRegionalString.contains("regionalValue"));
        assertTrue(scoreRegionalString.contains("divisionValue"));
        assertTrue(scoreRegionalString.contains("20"));
        assertTrue(scoreRegionalString.contains("1"));
    }

}
