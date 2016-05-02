package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.chrisyoung.foos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFeedFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    public GameFeedFragment() {
        // Required empty public constructor
    }


    // newInstance constructor for creating fragment with arguments
    public static GameFeedFragment newInstance(int page, String title) {
        GameFeedFragment fragmentGameFeed = new GameFeedFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentGameFeed.setArguments(args);
        return fragmentGameFeed;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_feed, container, false);
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);
        return view;
    }
}
