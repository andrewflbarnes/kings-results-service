package org.kingsski.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "kings_teams", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"league", "division", "position"})
})
public class DisplayableTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "team")
    private String team;
    @Column(name = "league")
    private String league;
    @Column(name = "division")
    private String division;
    @Column(name = "position")
    private int position;
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
    @Transient
    private int orderedScore1;
    @Transient
    private int orderedScore2;
    @Transient
    private int orderedScore3;
    @Transient
    private int orderedScore4;

    public DisplayableTeam(String team, String league, String division, int position, int r1, int r2, int r3, int r4) {
        this.team = team;
        this.league = league;
        this.division = division;
        this. position = position;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        updateScores();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
        this.updateScores();
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
        this.updateScores();
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
        this.updateScores();
    }

    public int getR4() {
        return r4;
    }

    public void setR4(int r4) {
        this.r4 = r4;
        this.updateScores();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOrderedScore1() {
        return this.orderedScore1;
    }

    public int getOrderedScore2() {
        return this.orderedScore2;
    }

    public int getOrderedScore3() {
        return this.orderedScore3;
    }

    public int getOrderedScore4() {
        return this.orderedScore4;
    }

    public void updateScores() {
        int[] scores = new int[]{this.r1, this.r2, this.r3, this.r4};

        for(int i = 0; i < 3; ++i) {
            for(int j = i + 1; j < 4; ++j) {
                if (scores[i] < scores[j]) {
                    int tempScore = scores[i];
                    scores[i] = scores[j];
                    scores[j] = tempScore;
                }
            }
        }

        this.orderedScore1 = scores[0];
        this.orderedScore2 = scores[1];
        this.orderedScore3 = scores[2];
        this.orderedScore4 = scores[3];
        this.total = this.r1 + this.r2 + this.r3 + this.r4;
    }
}
