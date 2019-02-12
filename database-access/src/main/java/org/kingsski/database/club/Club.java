package org.kingsski.database.club;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Model reflecting the CLUB view in the database
 */
public class Club {

    private long id;
    private String name;
    private long teamCount;

    public long getId() {
        return id;
    }

    public Club id(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Club name(String name) {
        this.name = name;
        return this;
    }

    public long getTeamCount() {
        return teamCount;
    }

    public Club teamCount(long teamCount) {
        this.teamCount = teamCount;
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

        if (!(obj instanceof Club)) {
            return false;
        }

        Club other = (Club)obj;

        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.name, other.name)
                .isEquals();
    }

    @Override
    public String toString() {
        return "Club[id:" + id +
                ",name:" + name +
                ",team count:" + teamCount +
                "]";
    }
}
