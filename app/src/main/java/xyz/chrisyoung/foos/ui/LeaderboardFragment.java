package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import xyz.chrisyoung.foos.adapters.LeaderboardListAdapter;
import xyz.chrisyoung.foos.models.User;

public class LeaderboardFragment extends Fragment {
    public static final String TAG = LeaderboardFragment.class.getSimpleName();

    private Query mQuery;
    private Firebase mFirebaseUsersRef;
    private FirebaseLeaderboardListAdapter mAdapter;

//    protected RecyclerView.LayoutManager mLayoutManager;
//    protected LeaderboardListAdapter mLeaderboardListAdapter;

    @Bind(R.id.leaderboardRecyclerView) RecyclerView mRecyclerView;

//    public ArrayList<User> mUsers = new ArrayList<>();


    // newInstance constructor for creating fragment with arguments
    public static LeaderboardFragment newInstance(int page) {
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        return fragmentLeaderboard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        for (int i = 0; i < allUserFirstNames.length; i++) {
//            User user = new User();
//            user.setFirstName(allUserFirstNames[i]);
//            user.setLastName(allUserLastNames[i]);
//            user.setEmail(allUserEmails[i]);
//            mUsers.add(user);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ButterKnife.bind(this, view);

        mFirebaseUsersRef = new Firebase(Constants.FIREBASE_URL_USERS);

        setUpFirebaseQuery();
        setUpRecyclerView();

//        mLeaderboardListAdapter = new LeaderboardListAdapter(getActivity(), mUsers);
//        mRecyclerView.setAdapter(mLeaderboardListAdapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseUsersRef.toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseLeaderboardListAdapter(mQuery, User.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
