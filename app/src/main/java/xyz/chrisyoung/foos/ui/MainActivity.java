package xyz.chrisyoung.foos.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.util.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.loginButton) TextView mLoginButton;
    @Bind(R.id.signUpButton) TextView mSignUpButton;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/bebasneue.ttf");
        mLoginButton.setTypeface(myCustomFont);
        mSignUpButton.setTypeface(myCustomFont);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mSharedPreferencesEditor.putString(Constants.KEY_UID, user.getUid()).apply();
                    // User is signed in
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } if (v == mSignUpButton) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}
