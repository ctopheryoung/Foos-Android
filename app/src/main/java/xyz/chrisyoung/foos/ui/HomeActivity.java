package xyz.chrisyoung.foos.ui;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

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
import xyz.chrisyoung.foos.adapters.HomeFragmentAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mFirebaseRef;

    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;
    @Bind(R.id.recordGameButton) Button mRecordGameButton;

    FragmentPagerAdapter adapterViewPager;

    public String[] players = new String[] {"Summer", "Cooper", "Sarah", "Eric", "Chris", "Ben"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new HomeFragmentAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        ButterKnife.bind(this);
        mRecordGameButton.setOnClickListener(this);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        mWelcomeTextView.setText("Welcome, " + userEmail);


        //TRUE SKILL RANKING EXPLORATION
        //Creates new players with Ids
        Player<String> player1 = new Player<>("Player 1");
        Player<String> player2 = new Player<>("Player 2");

        GameInfo gameInfo = GameInfo.getDefaultGameInfo();


        //Sets up Game with each player on their own team vs each other
        Team team1 = new Team(player1, gameInfo.getDefaultRating());
        Team team2 = new Team(player2, gameInfo.getDefaultRating());

        Collection<ITeam> teams = Team.concat(team1, team2);

        //Calculates new rating based on rankings of teams... last param of calculateNewRatings.....↓
        Map<IPlayer, Rating> newRatings = TrueSkillCalculator.calculateNewRatings(gameInfo, teams, 1, 2);

        Rating player1Rating = newRatings.get(player1);
        Rating player2Rating = newRatings.get(player2);

        Log.d(TAG, "Player 1, game 1: " + player1Rating);

        //GAME 2 AGAINST 3RD (NEW) PLAYER
        Player<String> player3 = new Player<String>("Player 3");
        Team team3 = new Team(player1, player1Rating);
        Team team4 = new Team(player3, gameInfo.getDefaultRating());

        Collection<ITeam> teams2 = Team.concat(team3, team4);

        Map<IPlayer, Rating> newRatings2 = TrueSkillCalculator.calculateNewRatings(gameInfo, teams2, 1, 3);

        player1Rating = newRatings2.get(player1);

        Log.d(TAG, "Player 1, game 2: " + player1Rating);
    }

    @Override
    public void onClick(View v) {
        if (v == mRecordGameButton) {
            Intent intent = new Intent(HomeActivity.this, RecordGameActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void logout() {
        mFirebaseRef.unauth();
        takeUserToMainOnUnAuth();
    }

    private void takeUserToMainOnUnAuth() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
