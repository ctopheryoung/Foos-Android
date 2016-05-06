package xyz.chrisyoung.foos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;

/**
 * Created by topher on 4/29/16.
 */
public class GameFeedListAdapter extends RecyclerView.Adapter<GameFeedViewHolder> {
    public static final String TAG = LeaderboardListAdapter.class.getSimpleName();
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameFeedListAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @Override
    public GameFeedViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_feed_list_item, parent, false);
        GameFeedViewHolder viewHolder = new GameFeedViewHolder(view, mGames);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameFeedViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

}
