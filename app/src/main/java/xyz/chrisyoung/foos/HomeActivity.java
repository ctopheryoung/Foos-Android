package xyz.chrisyoung.foos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class HomeActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;
    @Bind(R.id.leaderboard) ListView mLeaderboard;
    @Bind(R.id.ratingTextView) TextView mRatingTextView;

    public String[] players = new String[] {"Summer", "Cooper", "Sarah", "Eric", "Chris", "Ben", "7. Emily", "8. Mike", "9. Matt", "10. Nolan", "11. Steve", "12. Michelle", "13. Mary", "14. Ramone", "15. Trevor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, players);
        mLeaderboard.setAdapter(adapter);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        mWelcomeTextView.setText("Welcome, " + userEmail);


        //TRUESKILL RANKING EXPLORATION

        //Creates new players with Ids
        Player<Integer> player1 = new Player<Integer>(1);
        Player<Integer> player2 = new Player<Integer>(2);

        GameInfo gameInfo = GameInfo.getDefaultGameInfo();

        Log.d(TAG, "Initial Player 1: " + player1);

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
        Player<Integer> player3 = new Player<Integer>(3);
        Team team3 = new Team(player1, player1Rating);
        Team team4 = new Team(player3, gameInfo.getDefaultRating());

        Collection<ITeam> teams2 = Team.concat(team3, team4);

        Map<IPlayer, Rating> newRatings2 = TrueSkillCalculator.calculateNewRatings(gameInfo, teams2, 1, 3);

        player1Rating = newRatings2.get(player1);

        Log.d(TAG, "Player 1, game 2: " + player1Rating);
    }
}
