package org.kingsski.api.model;

import org.kingsski.wax.data.Team;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kings_teams")
public class WordpressTeam {

    @EmbeddedId
    private WordpressTeamPK wordpressTeamPK;
    @Column(name = "team")
    private String team;
    @Column(name = "r1")
    private int r1;
    @Column(name = "r2")
    private int r2;
    @Column(name = "r3")
    private int r3;
    @Column(name = "r4")
    private int r4;
    @Column(name = "total")
    private int total;

    public WordpressTeamPK getWordpressTeamPK() {
        return wordpressTeamPK;
    }

    public String getTeam() {
        return team;
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public int getR3() {
        return r3;
    }

    public int getR4() {
        return r4;
    }

    public int getTotal() {
        return total;
    }

    public Team asTeam() {
        Team team = new Team();
        team.setLeague(getWordpressTeamPK().getLeague());
        team.setDivision(getWordpressTeamPK().getDivision());
        team.setScoreR1(getR1());
        team.setScoreR2(getR2());
        team.setScoreR3(getR3());
        team.setScoreR4(getR4());
        team.setTeamName(getTeam());

        return team;
    }
}
