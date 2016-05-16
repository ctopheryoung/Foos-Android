package xyz.chrisyoung.foos.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.HomeFragmentAdapter;
import xyz.chrisyoung.foos.models.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mFirebaseRef;
    private Firebase mUsersRef;
    private ValueEventListener mUserRefListener;
    private SharedPreferences mSharedPreferences;
    private String mUId;

    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;
    @Bind(R.id.recordGameButton) Button mRecordGameButton;
    @Bind(R.id.recordTextView) TextView mRecordTextView;
    @Bind(R.id.totalGamesTextView) TextView mTotalGamesTextView;
    @Bind(R.id.userRatingTextView) TextView mUserRatingTextView;

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUId = mSharedPreferences.getString(Constants.KEY_UID, null);
        mUsersRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mUId);

        mUserRefListener = mUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mWelcomeTextView.setText(user.getFullName());
                mRecordTextView.setText("Wins: "+user.getWins()+"   Losses: "+user.getLosses());
                Integer totalGames = user.getWins()+user.getLosses();
                mTotalGamesTextView.setText(totalGames.toString()+" Games Total");
                mUserRatingTextView.setText(user.getTrueSkill().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Read failed");
            }
        });

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new HomeFragmentAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        mRecordGameButton.setOnClickListener(this);
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
