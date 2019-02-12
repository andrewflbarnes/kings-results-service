package org.kingsski.database.competition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Model reflecting the COMPETITION view in the database
 */
public class Competition {

    private long id;
    private String name;
    private String organisation;

    public long getId() {
        return id;
    }

    public Competition id(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Competition name(String name) {
        this.name = name;
        return this;
    }

    public String getOrganisation() {
        return organisation;
    }

    public Competition organisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Competition)) {
            return false;
        }

        Competition other = (Competition)obj;

        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.name, other.name)
                .append(this.organisation, other.organisation)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Competition[id:" + id +
                ",name:" + name +
                ",organisation:" + organisation +
                "]";
    }
}
