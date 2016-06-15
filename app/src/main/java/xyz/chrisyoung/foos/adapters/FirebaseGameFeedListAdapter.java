package xyz.chrisyoung.foos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.Query;

import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;
import xyz.chrisyoung.foos.util.FirebaseRecyclerAdapter;

/**
 * Created by topher on 5/6/16.
 */
public class FirebaseGameFeedListAdapter extends FirebaseRecyclerAdapter<GameFeedViewHolder, Game> {

    public FirebaseGameFeedListAdapter(Query query, Class<Game> itemClass) {
        super(query, itemClass);
    }

    @Override
    public GameFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_feed_list_item, parent, false);
        return new GameFeedViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(GameFeedViewHolder holder, int position) {
        holder.bindGame(getItem(position));
    }

    @Override
    protected void itemAdded(Game item, String key, int position) {

    }

    @Override
    protected void itemChanged(Game oldItem, Game newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Game item, String key, int position) {

    }

    @Override
    protected void itemMoved(Game item, String key, int oldPosition, int newPosition) {

    }
}