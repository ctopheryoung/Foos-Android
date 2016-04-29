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
import xyz.chrisyoung.foos.models.User;

/**
 * Created by topher on 4/29/16.
 */
public class LeaderboardListAdapter extends RecyclerView.Adapter<LeaderboardListAdapter.UserViewHolder> {
    private ArrayList<User> mUsers = new ArrayList<>();
    private Context mContext;

    public LeaderboardListAdapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public LeaderboardListAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_list_item, parent, false);
        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LeaderboardListAdapter.UserViewHolder holder, int position) {
        holder.bindUser(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.rankTextView) TextView mRankTextView;
        @Bind(R.id.nameTextView) TextView mNameTextView;
        @Bind(R.id.skillTextView) TextView mSkillTextView;
        @Bind(R.id.winsTextView) TextView mWinsTextView;
        @Bind(R.id.lossesTextView) TextView mLossesTextView;
        private Context mContext;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindUser(User user) {
            mNameTextView.setText(user.getFullName());
        }

    }

}
