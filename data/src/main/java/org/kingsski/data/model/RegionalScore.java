package org.kingsski.data.model;

public class RegionalScore implements Comparable {
    private String name;
    private int score;

    public RegionalScore() {
        // default constructor
    }

    public RegionalScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (RegionalScore.class.equals(obj.getClass())) {
            RegionalScore other = (RegionalScore)obj;

            if (this.name == null) {
                return false;
            }

            return this.name.equals(other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + (name == null ? 0 : name.hashCode());

        return result;
    }

    @Override
    public int compareTo(Object obj) {
        if (this == obj) {
            return 0;
        }

        if (obj == null) {
            return -1;
        }

        if (RegionalScore.class.isAssignableFrom(obj.getClass())) {
            RegionalScore other = (RegionalScore)obj;
            if (other.getName() == null) {
                return -1;
            }

            if (this.name == null) {
                return 1;
            }

            return this.name.compareTo(other.name);

        }

        return -1;
    }

    @Override
    public String toString() {
        return "RegionalScore[" +
                "regional:" + name + ";" +
                "score:" + score
                + "]";
    }
}
