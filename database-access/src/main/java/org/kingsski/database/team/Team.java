package org.kingsski.database.team;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Model reflecting the TEAM view in the database
 */
public class Team {

    private long id;
    private String name;
    private String club;

    public long getId() {
        return id;
    }

    public Team id(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public String getClub() {
        return club;
    }

    public Team club(String club) {
        this.club = club;
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(club)
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

        if (!(obj instanceof Team)) {
            return false;
        }

        Team other = (Team)obj;

        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.name, other.name)
                .append(this.club, other.club)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Team[id:" + id +
                ",name:" + name +
                ",club:" + club +
                "]";
    }
}
