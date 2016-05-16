package xyz.chrisyoung.foos.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import jskills.GameInfo;
import jskills.Player;
import jskills.Team;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;
import xyz.chrisyoung.foos.models.User;

public class RecordGameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RecordGameActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private Firebase mFirebaseUsersRef;
    private Query mWinnerQuery;
    private String mWinnerId;
    private String mLoserId;
    private Query mLoserQuery;
    public ArrayList<User> mUsers = new ArrayList<>();

    @Bind(R.id.winnerScoreEditText) EditText mWinnerScoreEditText;
    @Bind(R.id.loserScoreEditText) EditText mLoserScoreEditText;
    @Bind(R.id.saveButton) Button mSaveButton;
    @Bind(R.id.winnerSpinner) Spinner mWinnerSpinner;
    @Bind(R.id.loserSpinner) Spinner mLoserSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_game);
        ButterKnife.bind(this);
        mFirebaseUsersRef = new Firebase(Constants.FIREBASE_URL_USERS);
        mSaveButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setUpSpinners();
    }

    private void setUpSpinners(){
        mFirebaseUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUsers.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    mUsers.add(postSnapshot.getValue(User.class));
                }
                ArrayAdapter adapter = new ArrayAdapter(RecordGameActivity.this, android.R.layout.simple_spinner_item, mUsers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mWinnerSpinner.setAdapter(adapter);
                mLoserSpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == mSaveButton) {
            User winner = (User)mWinnerSpinner.getSelectedItem();
            User loser = (User)mLoserSpinner.getSelectedItem();
            Integer winnerScore = Integer.parseInt(mWinnerScoreEditText.getText().toString());
            Integer loserScore = Integer.parseInt(mLoserScoreEditText.getText().toString());
            String currentUserId = mSharedPreferences.getString(Constants.KEY_UID, null);

// This was my attempt at pulling user Ids out of Firebase when I had names in the Spinners.
//            mWinnerQuery = mFirebaseUsersRef.orderByChild("fullName").equalTo(winnerName);
//
//            mWinnerQuery.addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    mWinnerId=dataSnapshot.getKey();
//                    Log.d(TAG, "mWinnerID in Event Listener: " + mWinnerId);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(FirebaseError firebaseError) {
//
//                }
//            });

            // 1. Find both players
            // 2. Find each players rating
            // 3. Calculate new ratings
            // 4. Update ratings and calculate new TrueSkill -> playerObject.updateRating(newMean, newStdDev)

            Player<User> playerWinner = new Player<>(winner);
            Player<User> playerLoser = new Player<>(loser);
            GameInfo gameInfo = GameInfo.getDefaultGameInfo();

            Game newGame = new Game(winner.getFullName(), winner.getPushId(), loser.getFullName(), loser.getPushId(), winnerScore, loserScore, currentUserId);
            saveGameToFirebase(newGame);
//            updateWinnerScore();
//            updateLoserScore();
        }
    }

    public void saveGameToFirebase(Game game) {
        //Saves game and single entity in "games" child node
        Firebase gamesRef = new Firebase(Constants.FIREBASE_URL_GAMES);
        Firebase newGameRef = gamesRef.push();
        String pushId = newGameRef.getKey();
        game.setPushId(pushId);
        newGameRef.setValue(game);

        //Saves game in playerGames child once for each player (with under nodes with winnerID and loserID)
        Firebase playerGamesRef = new Firebase(Constants.FIREBASE_URL_PLAYER_GAMES);
        playerGamesRef.child(game.getWinnerId()).child(pushId).setValue(game);
        playerGamesRef.child(game.getLoserId()).child(pushId).setValue(game);
    }
}
