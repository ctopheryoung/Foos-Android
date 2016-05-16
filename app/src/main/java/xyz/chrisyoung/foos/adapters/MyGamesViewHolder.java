package xyz.chrisyoung.foos.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.Constants;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.Game;

/**
 * Created by topher on 5/13/16.
 */
public class MyGamesViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.scoreTextView) TextView mScoreTextView;
    @Bind(R.id.outcomeTextView) TextView mOutcomeTextView;
    @Bind(R.id.resultTextView) TextView mResultTextView;
    @Bind(R.id.timeTextView) TextView mTimeTextView;

    private SharedPreferences mSharedPreferences;
    private String mUId;

    private Context mContext;
    private ArrayList<Game> mGames = new ArrayList<>();

    public MyGamesViewHolder(View itemView, ArrayList<Game> games) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindGame(Game game) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mUId = mSharedPreferences.getString(Constants.KEY_UID, null);
        mTimeTextView.setText(game.displayHumanTime());
        mScoreTextView.setText(game.getWinnerScore() + " to " + game.getLoserScore());
        if (mUId.equals(game.getWinnerId())) {
            mResultTextView.setText("W");
            mOutcomeTextView.setText("Defeated " + game.getLoserName());
        } else {
            mResultTextView.setText("L");
            mOutcomeTextView.setText("Lost to " + game.getWinnerName());
        }
    }
}
