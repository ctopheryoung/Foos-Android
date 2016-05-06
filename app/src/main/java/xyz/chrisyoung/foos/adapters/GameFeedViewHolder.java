package xyz.chrisyoung.foos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;

/**
 * Created by topher on 5/6/16.
 */
public class GameFeedViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.scoreTextView) TextView mScoreTextView;
    @Bind(R.id.resultTextView) TextView mResultTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;

    private Context mContext;
    private ArrayList<Game> mGames = new ArrayList<>();

    public GameFeedViewHolder(View itemView, ArrayList<Game> games) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindGame(Game game) {
        mResultTextView.setText(game.getWinner() + " beat " + game.getLoser());
        mScoreTextView.setText(game.getWinnerScore() + " to " + game.getLoserScore());
        mTimeTextView.setText(game.displayHumanTime());
    }

}