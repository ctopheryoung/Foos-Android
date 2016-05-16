package xyz.chrisyoung.foos.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import jskills.GameInfo;
import jskills.IPlayer;
import jskills.ITeam;
import jskills.Player;
import jskills.Rating;
import jskills.Team;
import jskills.TrueSkillCalculator;
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

            //Setup Winner Player
            Player<User> playerWinner = new Player<>(winner);
            Rating initialWinnerRating = new Rating(winner.getMean(), winner.getStandardDeviation());
            Team winnerTeam = new Team(playerWinner, initialWinnerRating);

            //Setup Loser Player
            Player<User> playerLoser = new Player<>(loser);
            Rating initialLoserRating = new Rating(loser.getMean(), loser.getStandardDeviation());
            Team loserTeam = new Team(playerLoser, initialLoserRating);

            //Setup Game
            GameInfo gameInfo = GameInfo.getDefaultGameInfo();
            Collection<ITeam> teams = Team.concat(winnerTeam, loserTeam);
            Map<IPlayer, Rating> newRatings = TrueSkillCalculator.calculateNewRatings(gameInfo, teams, 1, 2);

            //Store Winner Game Results
            Rating newWinnerRating = newRatings.get(playerWinner);
            winner.countWin();
            winner.updateRating(newWinnerRating.getMean(), newWinnerRating.getStandardDeviation());
            mFirebaseUsersRef.child(winner.getPushId()).setValue(winner);

            //Store Loser Game Results
            Rating newLoserRating = newRatings.get(playerLoser);
            loser.countLoss();
            loser.updateRating(newLoserRating.getMean(), newLoserRating.getStandardDeviation());
            mFirebaseUsersRef.child(loser.getPushId()).setValue(loser);

            //Store Game Record
            Game newGame = new Game(winner.getFullName(), winner.getPushId(), loser.getFullName(), loser.getPushId(), winnerScore, loserScore, currentUserId);
            saveGameToFirebase(newGame);
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
