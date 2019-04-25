package org.kingsski.kaas.database.organisation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.kingsski.kaas.TestUtil.assertEqualsStrong;
import static org.kingsski.kaas.TestUtil.assertNotEqualsStrong;

public class OrganisationTest {

    private static final int DEFAULT_ID = 1;
    private static final String DEFAULT_NAME = "";
    private static final int DEFAULT_COMPETITION_COUNT = 2;

    private Organisation.OrganisationBuilder resetBuilder(Organisation.OrganisationBuilder builder) {
        builder.id(DEFAULT_ID)
                .name(DEFAULT_NAME)
                .competitionCount(DEFAULT_COMPETITION_COUNT);

        return builder;
    }

    @Test
    public void equalityTests() {
        Organisation.OrganisationBuilder builder = Organisation.builder();
        resetBuilder(builder);

        Organisation defaultOrganisation = builder.build();

        assertEqualsStrong(
                defaultOrganisation,
                builder.build(),
                resetBuilder(builder).competitionCount(9999).build()

        );

        assertFalse(defaultOrganisation.equals(null));
        assertNotEqualsStrong(
                defaultOrganisation,
                resetBuilder(builder).name("boom").build(),
                resetBuilder(builder).name(null).build(),
                resetBuilder(builder).id(9999).build(),
                "boom"
        );
    }

    @Test
    public void string() {
        Organisation organisation = resetBuilder(Organisation.builder()).build();

        String organisationString = organisation.toString();
        assertTrue(organisationString.contains(DEFAULT_NAME));
        assertTrue(organisationString.contains(String.valueOf(DEFAULT_ID)));
        assertTrue(organisationString.contains(String.valueOf(DEFAULT_COMPETITION_COUNT)));
    }

}
