package xyz.chrisyoung.foos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.welcomeTextView) TextView mWelcomeTextView;
    @Bind(R.id.leaderboard) ListView mLeaderboard;

    public String[] players = new String[] {"1. Summer", "2. Cooper", "3. Sarah", "4. Eric", "5. Chris", "6. Ben", "7. Emily", "8. Mike", "9. Matt", "10. Nolan", "11. Steve", "12. Michelle", "13. Mary", "14. Ramone", "15. Trevor"};

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
    }
}
