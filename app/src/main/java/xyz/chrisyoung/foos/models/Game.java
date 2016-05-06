package xyz.chrisyoung.foos.models;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by Guest on 4/28/16.
 */
public class Game {
    private String winner;
    private String loser;
    private int winnerScore;
    private int loserScore;
    private long timeStamp;
    private String createdBy;
    private String pushId;

    public Game() {}

    public Game(String winner, String loser, int winnerScore, int loserScore, String createdBy) {
        this.winner = winner;
        this.loser = loser;
        this.winnerScore = winnerScore;
        this.loserScore = loserScore;
        this.createdBy = createdBy;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getWinner() {
        return winner;
    }

    public String getLoser() {
        return loser;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public int getLoserScore() {
        return loserScore;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getCreatedBy () { return createdBy; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public CharSequence displayHumanTime() {
        return DateUtils.getRelativeTimeSpanString(timeStamp);
    }
}

