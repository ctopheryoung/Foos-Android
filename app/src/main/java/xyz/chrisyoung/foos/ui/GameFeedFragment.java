package xyz.chrisyoung.foos.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.adapters.FirebaseGameFeedListAdapter;
import xyz.chrisyoung.foos.models.Game;

public class GameFeedFragment extends Fragment {
    public static final String TAG = GameFeedFragment.class.getSimpleName();

    private Query mQuery;
    private Firebase mFirebaseGamesRef;
    private FirebaseGameFeedListAdapter mAdapter;

    @Bind(R.id.gameFeedRecyclerView) RecyclerView mRecyclerView;

    public static GameFeedFragment newInstance(int page) {
        GameFeedFragment fragmentGameFeed = new GameFeedFragment();
        return fragmentGameFeed;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_feed, container, false);
        ButterKnife.bind(this, view);

        mFirebaseGamesRef = new Firebase(Constants.FIREBASE_URL_GAMES);

        setUpFirebaseQuery();
        setUpRecyclerView();

        return view;
    }

    private void setUpFirebaseQuery() {
        String location = mFirebaseGamesRef.toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseGameFeedListAdapter(mQuery, Game.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
