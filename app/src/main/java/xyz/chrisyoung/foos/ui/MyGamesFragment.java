package xyz.chrisyoung.foos.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.FirebaseMyGamesListAdapter;
import xyz.chrisyoung.foos.models.Game;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGamesFragment extends Fragment {
    public static final String TAG = GameFeedFragment.class.getSimpleName();
    private Query mQuery;
    private Firebase mFirebasePlayerGamesRef;
    private FirebaseMyGamesListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;

    @Bind(R.id.myGamesRecyclerView) RecyclerView mRecyclerView;

    public static MyGamesFragment newInstance(int page) {
        MyGamesFragment fragmentMyGames = new MyGamesFragment();
        return fragmentMyGames;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_games, container, false);
        ButterKnife.bind(this, view);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mFirebasePlayerGamesRef = new Firebase(Constants.FIREBASE_URL_PLAYER_GAMES);

        setUpFirebaseQuery();
        setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String location = mFirebasePlayerGamesRef.child(userUid).toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseMyGamesListAdapter(mQuery, Game.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}