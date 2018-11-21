package org.kingsski.database.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Model reflecting the ORGANISATION view in the database
 */
public class Organisation {

    private long id;
    private String name;
    private long competitionCount;

    public long getId() {
        return id;
    }

    public Organisation id(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Organisation name(String name) {
        this.name = name;
        return this;
    }

    public long getCompetitionCount() {
        return competitionCount;
    }

    public Organisation competitionCount(long competitionCount) {
        this.competitionCount = competitionCount;
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

        if (!(obj instanceof Organisation)) {
            return false;
        }

        Organisation other = (Organisation)obj;

        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.name, other.name)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Organisation[id:" + id +
                ",name:" + name +
                ",competition count:" + competitionCount +
                "]";
    }
}
