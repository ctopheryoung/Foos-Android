package xyz.chrisyoung.foos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.Query;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.User;
import xyz.chrisyoung.foos.util.FirebaseRecyclerAdapter;

/**
 * Created by topher on 5/6/16.
 */
public class FirebaseLeaderboardListAdapter extends FirebaseRecyclerAdapter<UserViewHolder, User> {

    public FirebaseLeaderboardListAdapter(Query query, Class<User> itemClass) {
        super(query, itemClass);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_list_item, parent, false);
        return new UserViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bindUser(getItem(position));
    }

    @Override
    protected void itemAdded(User item, String key, int position) {

    }

    @Override
    protected void itemChanged(User oldItem, User newItem, String key, int position) {
    }

    @Override
    protected void itemRemoved(User item, String key, int position) {

    }

    @Override
    protected void itemMoved(User item, String key, int oldPosition, int newPosition) {

    }
}