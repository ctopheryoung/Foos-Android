package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.LeaderboardListAdapter;
import xyz.chrisyoung.foos.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {
    // Store instance variables
    public static final String TAG = LeaderboardFragment.class.getSimpleName();
    private LeaderboardListAdapter mLeaderboardListAdapter;
    public ArrayList<User> mUsers = new ArrayList<>();

    @Bind(R.id.)

    //Put hard-coded data here

    public LeaderboardFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static LeaderboardFragment newInstance(int page, String title) {
        LeaderboardFragment fragmentLeaderboard = new LeaderboardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentLeaderboard.setArguments(args);
        return fragmentLeaderboard;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        return view;
    }
}
