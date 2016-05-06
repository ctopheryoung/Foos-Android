package xyz.chrisyoung.foos.models;

import java.util.Date;

/**
 * Created by Guest on 4/28/16.
 */
public class Game {
    private String winner;
    private String loser;
    private int winnerScore;
    private int loserScore;
    private Date timeStamp;
    private String createdBy;
    private String pushId;

    public Game() {}

    public Game(String winner, String loser, int winnerScore, int loserScore, String createdBy) {
        this.winner = winner;
        this.loser = loser;
        this.winnerScore = winnerScore;
        this.loserScore = loserScore;
        this.createdBy = createdBy;
        this.timeStamp = new Date();
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(int winnerScore) {
        this.winnerScore = winnerScore;
    }

    public int getLoserScore() {
        return loserScore;
    }

    public void setLoserScore(int loserScore) {
        this.loserScore = loserScore;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCreatedBy () { return createdBy; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

