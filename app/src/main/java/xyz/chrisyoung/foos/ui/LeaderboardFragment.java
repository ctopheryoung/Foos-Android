package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.FirebaseLeaderboardListAdapter;
//import xyz.chrisyoung.foos.adapters.LeaderboardListAdapter;
import xyz.chrisyoung.foos.models.User;

public class LeaderboardFragment extends Fragment {
    public static final String TAG = LeaderboardFragment.class.getSimpleName();

    private Query mQuery;
    private Firebase mFirebaseUsersRef;
    private FirebaseLeaderboardListAdapter mAdapter;

    @Bind(R.id.leaderboardRecyclerView) RecyclerView mRecyclerView;

    public static LeaderboardFragment newInstance(int page) {
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        return fragmentLeaderboard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ButterKnife.bind(this, view);

        mFirebaseUsersRef = new Firebase(Constants.FIREBASE_URL_USERS);

        setUpFirebaseQuery();
        setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseUsersRef.toString();
        mQuery = new Firebase(location).orderByChild("trueSkillInverse");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseLeaderboardListAdapter(mQuery, User.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
