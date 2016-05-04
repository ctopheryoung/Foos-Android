package xyz.chrisyoung.foos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;

/**
 * Created by topher on 4/29/16.
 */
public class GameFeedListAdapter extends RecyclerView.Adapter<GameFeedListAdapter.GameViewHolder> {
    public static final String TAG = GameFeedListAdapter.class.getSimpleName();
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameFeedListAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @Override
    public GameFeedListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_feed_list_item, parent, false);
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameFeedListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() { return mGames.size();}

    public class GameViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.resultTextView) TextView mResultTextView;
        private Context mContext;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGame(Game game) {
            mResultTextView.setText(game.getWinner() + " beat " + game.getLoser());
        }
    }
}
