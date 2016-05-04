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
public class MyGamesFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    public MyGamesFragment() {
        // Required empty public constructor
    }


    // newInstance constructor for creating fragment with arguments
    public static MyGamesFragment newInstance(int page) {
        MyGamesFragment fragmentMyGames = new MyGamesFragment();
        return fragmentMyGames;
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
        View view = inflater.inflate(R.layout.fragment_my_games, container, false);
        return view;
    }
}