package xyz.chrisyoung.foos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.chrisyoung.foos.R;
import xyz.chrisyoung.foos.models.User;

/**
 * Created by topher on 5/6/16.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.rankTextView) TextView mRankTextView;
    @Bind(R.id.nameTextView) TextView mNameTextView;
    @Bind(R.id.skillTextView) TextView mSkillTextView;
    @Bind(R.id.recordTextView) TextView mWinsTextView;
    @Bind(R.id.lossesTextView) TextView mLossesTextView;

    private Context mContext;
    private ArrayList<User> mUsers = new ArrayList<>();

    public UserViewHolder(View itemView, ArrayList<User> users) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
    }

    public void bindUser(User user) {
        mNameTextView.setText(user.getFullName());
        Integer rank = getAdapterPosition() + 1;
        mRankTextView.setText(rank.toString() + ".");
        mWinsTextView.setText(user.getWins().toString());
        mLossesTextView.setText(user.getLosses().toString());
        Double trueSkill = Math.floor(user.getMean()-3*user.getStandardDeviation());
        mSkillTextView.setText(trueSkill.toString());
    }

}