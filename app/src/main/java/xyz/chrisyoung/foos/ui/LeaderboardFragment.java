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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.LeaderboardListAdapter;
import xyz.chrisyoung.foos.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {
    public static final String TAG = LeaderboardFragment.class.getSimpleName();

    protected RecyclerView.LayoutManager mLayoutManager;
    protected LeaderboardListAdapter mLeaderboardListAdapter;
    @Bind(R.id.leaderboardRecyclerView) RecyclerView mRecyclerView;

    public ArrayList<User> mUsers = new ArrayList<>();

    String[] allUserFirstNames = new String[] {
            "Chris", "Summer", "Special Agent", "Perry", "Michelle"
    };

    String[] allUserLastNames = new String [] {
            "Young", "Brochtrup", "Dale Cooper", "Eisling", "Brecunier"
    };

    String [] allUserEmails = new String [] {
            "ctopheryoung@gmail.com", "summer@epicodus.com", "ImADog@doghouse.com", "perry@epicodus.com", "michelle@website.com"
    };

    // newInstance constructor for creating fragment with arguments
    public static LeaderboardFragment newInstance(int page) {
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        return fragmentLeaderboard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < allUserFirstNames.length; i++) {
            User user = new User();
            user.setFirstName(allUserFirstNames[i]);
            user.setLastName(allUserLastNames[i]);
            user.setEmail(allUserEmails[i]);
            mUsers.add(user);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ButterKnife.bind(this, view);

        mLeaderboardListAdapter = new LeaderboardListAdapter(getContext(), mUsers);
        mRecyclerView.setAdapter(mLeaderboardListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        return view;
    }
}
