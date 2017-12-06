package org.kingsski.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class WordpressTeamPK implements Serializable {

    @Column(name = "league")
    private String league;
    @Column(name = "division")
    private String division;
    @Column(name = "position")
    private int position;

    public String getLeague() {
        return league;
    }

    public String getDivision() {
        return division;
    }

    public int getPosition() {
        return position;
    }
}
