package org.kingsski.kaas.database.scoreregional;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.AssertNotEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;

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

        ScoreRegional a = builder.build();
        // Things which should be equal
        ScoreRegional a2 = builder.build();
        ScoreRegional a3 = resetBuilder(builder).score(10).build();
        ScoreRegional a4 = resetBuilder(builder).position(2).build();
        // Things which should not be equal
        ScoreRegional b = resetBuilder(builder).organisation("boom").build();
        ScoreRegional c = resetBuilder(builder).competition("boom").build();
        ScoreRegional d = resetBuilder(builder).season("boom").build();
        ScoreRegional e = resetBuilder(builder).league("boom").build();
        ScoreRegional f = resetBuilder(builder).regional("boom").build();
        ScoreRegional g = resetBuilder(builder).division("boom").build();
        ScoreRegional h = resetBuilder(builder).club("boom").build();
        ScoreRegional i = resetBuilder(builder).team("boom").build();

        assertFalse(a.equals(null));
        assertFalse(a.equals("boom"));

        assertEqualsStrong(a, a);
        assertEqualsStrong(a, a2);
        assertEqualsStrong(a, a3);
        assertEqualsStrong(a, a4);
        assertEqualsStrong(a3, a4);

        AssertNotEqualsStrong(a, b);
        AssertNotEqualsStrong(a, c);
        AssertNotEqualsStrong(a, d);
        AssertNotEqualsStrong(a, e);
        AssertNotEqualsStrong(a, f);
        AssertNotEqualsStrong(a, g);
        AssertNotEqualsStrong(a, h);
        AssertNotEqualsStrong(a, i);
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
