package xyz.chrisyoung.foos.models;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by Guest on 4/28/16.
 */
public class Game {
    private String winnerName;
    private String winnerId;
    private String loserName;
    private String loserId;
    private int winnerScore;
    private int loserScore;
    private long timeStamp;
    private String createdBy;
    private String pushId;

    public Game() {}

    public Game(String winnerName, String winnerId, String loserName, String loserId, int winnerScore, int loserScore, String createdBy) {
        this.winnerName = winnerName;
        this.winnerId = winnerId;
        this.loserName = loserName;
        this.loserId = loserId;
        this.winnerScore = winnerScore;
        this.loserScore = loserScore;
        this.createdBy = createdBy;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public String getLoserName() {
        return loserName;
    }

    public String getLoserId() {
        return loserId;
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

