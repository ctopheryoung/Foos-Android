package xyz.chrisyoung.foos.models;

import jskills.Rating;

/**
 * Created by Guest on 4/28/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private Integer wins = 0;
    private Integer losses = 0;

    ////////////////////////////
    private Double mean = 25.0;
    private Double standardDeviation = 8.333333333333334;
    //Everytime mean and standardDeviation update (i.e. after a match then the following two properties need to be recalculated!
    private Double trueSkill = mean-3*standardDeviation;
    private Double trueSkillInverse = trueSkill*-1;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public Double getMean() {
        return mean;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void updateRating(Double mean, Double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.trueSkill = mean-3*standardDeviation;
        this.trueSkillInverse = trueSkill*-1;
    }
}
