package org.kingsski.data.model;

public class Individual {

    private long id;
    private String name;
    private String discipline;
    private String club;
    private int position;
    private int time1;
    private int time2;
    private int totalTime;

    public Individual() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (Individual.class.equals(obj.getClass())) {
            Individual other = (Individual)obj;

            if (this.discipline == null || this.club == null || this.name == null) {
                return false;
            }

            return this.discipline.equals(other.getDiscipline()) &&
                    this.club.equals(other.getClub()) &&
                    this.name.equals(other.getName());
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + (discipline == null ? 0 : discipline.hashCode());
        result = prime * result + (club == null ? 0 : club.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());

        return result;
    }
}
