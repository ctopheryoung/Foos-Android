package xyz.chrisyoung.foos;

/**
 * Created by topher on 5/4/16.
 */
public class Constants {
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;

    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_LOCATION_GAMES = "games";
    public static final String FIREBASE_LOCATION_PLAYER_GAMES = "playerGames";

    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
    public static final String FIREBASE_URL_GAMES = FIREBASE_URL + "/" + FIREBASE_LOCATION_GAMES;
    public static final String FIREBASE_URL_PLAYER_GAMES = FIREBASE_URL + "/" + FIREBASE_LOCATION_PLAYER_GAMES;

    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";

    public static final String KEY_USER_EMAIL = "email";
}
