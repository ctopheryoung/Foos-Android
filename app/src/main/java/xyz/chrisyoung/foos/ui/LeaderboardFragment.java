package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.util.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.FirebaseLeaderboardListAdapter;
//import xyz.chrisyoung.foos.adapters.LeaderboardListAdapter;
import xyz.chrisyoung.foos.models.User;

public class LeaderboardFragment extends Fragment {
    public static final String TAG = LeaderboardFragment.class.getSimpleName();

    private Query mQuery;
    private DatabaseReference mFirebaseUsersRef;
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

        mFirebaseUsersRef = FirebaseDatabase.getInstance().getReference().child("users");

        setUpFirebaseQuery();
        setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {
        mQuery = mFirebaseUsersRef.orderByChild("trueSkillInverse");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseLeaderboardListAdapter(mQuery, User.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
