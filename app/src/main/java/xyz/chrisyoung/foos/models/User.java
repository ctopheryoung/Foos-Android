package xyz.chrisyoung.foos.models;

/**
 * Created by Guest on 4/28/16.
 */
public class User {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private int mGameCount = 0;

    public User() {}

    public User (String firstName, String lastName, String email) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getFullName() {
        return mFirstName + " " + mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getGameCount() {
        return mGameCount;
    }

    public void setGameCount(int mGameCount) {
        this.mGameCount = mGameCount;
    }
}
