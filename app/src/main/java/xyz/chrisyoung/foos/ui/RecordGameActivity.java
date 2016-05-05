package xyz.chrisyoung.foos.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;

public class RecordGameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = RecordGameActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;

    @Bind(R.id.winnerSpinner) EditText mWinnerSpinner;
    @Bind(R.id.winnerScoreEditText) EditText mWinnerScoreEditText;
    @Bind(R.id.loserSpinner) EditText mLoserSpinner;
    @Bind(R.id.loserScoreEditText) EditText mLoserScoreEditText;
    @Bind(R.id.saveButton) Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_game);
        ButterKnife.bind(this);
        mSaveButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSaveButton) {
            String winner = mWinnerSpinner.getText().toString();
            Integer winnerScore = Integer.parseInt(mWinnerScoreEditText.getText().toString());
            String loser = mLoserSpinner.getText().toString();
            Integer loserScore = Integer.parseInt(mLoserScoreEditText.getText().toString());
            String userId = mSharedPreferences.getString(Constants.KEY_UID, null);

            Game newGame = new Game(winner, loser, winnerScore, loserScore, userId);
            saveGameToFirebase(newGame);
        }
    }

        public void saveGameToFirebase(Game game) {
            Firebase addedGameRef = new Firebase(Constants.FIREBASE_URL_GAMES);
            addedGameRef.push().setValue(game);

    }

}
