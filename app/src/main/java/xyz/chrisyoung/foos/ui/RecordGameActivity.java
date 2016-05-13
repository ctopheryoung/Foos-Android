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
    private ValueEventListener mWinnerIdRefListener;
    private ValueEventListener mLoserIdRefListener;
    private String mLoserId;
    private Query mLoserQuery;
    private ValueEventListener mUserIDRefListener;
    public ArrayList<String> mUsers = new ArrayList<>();

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
                    mUsers.add(postSnapshot.child("fullName").getValue().toString());
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
            String winnerName = mWinnerSpinner.getSelectedItem().toString();
            String loserName = mLoserSpinner.getSelectedItem().toString();

            mWinnerQuery = mFirebaseUsersRef.orderByChild("fullName").equalTo(winnerName);

            mWinnerQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mWinnerId=dataSnapshot.getKey();
                    Log.d(TAG, "Winner ID in datasnapshot: "+mWinnerId);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            Integer winnerScore = Integer.parseInt(mWinnerScoreEditText.getText().toString());
            Integer loserScore = Integer.parseInt(mLoserScoreEditText.getText().toString());
            String userId = mSharedPreferences.getString(Constants.KEY_UID, null);

            // 1. Find both players
            // 2. Find each players rating
            // 3. Calculate new ratings
            // 4. Update ratings and calculate new TrueSkill -> playerObject.updateRating(newMean, newStdDev)
            try {
                Thread.sleep(500);
                Log.d(TAG, "Sleep success.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Log.d(TAG, "Sleep problems");
            }
            Log.d(TAG, "Winner ID just before newGame object creation"+mWinnerId);
            Game newGame = new Game(mWinnerId, mLoserId, winnerScore, loserScore, userId);
            Log.d(TAG, "getWinner of newGame object="+newGame.getWinner());
//            saveGameToFirebase(newGame);
        }
    }

    public void saveGameToFirebase(Game game) {
        //Saves game and single entity in "games" child node
        Firebase gamesRef = new Firebase(Constants.FIREBASE_URL_GAMES);
        gamesRef.push().setValue(game);

        //Saves game in playerGames child once for each player (with under nodes with winnerID and loserID)
        Firebase playerGamesRef = new Firebase(Constants.FIREBASE_URL_PLAYER_GAMES);
        Firebase newGameRef = playerGamesRef.push();
        String pushId = newGameRef.getKey();
        game.setPushId(pushId);
        playerGamesRef.child(game.getWinner()).child(pushId).setValue(game);
        playerGamesRef.child(game.getLoser()).child(pushId).setValue(game);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mWinnerQuery.removeEventListener(mUserIDRefListener);
//        mLoserQuery.removeEventListener(mLoserIdRefListener);
//    }

}
