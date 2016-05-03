package xyz.chrisyoung.foos.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.chrisyoung.foos.ui.GameFeedFragment;
import xyz.chrisyoung.foos.ui.LeaderboardFragment;
import xyz.chrisyoung.foos.ui.MyGamesFragment;

/**
 * Created by topher on 4/29/16.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public HomeFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return LeaderboardFragment.newInstance(0);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return GameFeedFragment.newInstance(1);
            case 2: // Fragment # 1 - This will show SecondFragment
                return MyGamesFragment.newInstance(2);
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Leaderboard";
            case 1:
                return "Foos Game Feed";
            case 2:
                return "My Games";
            default:
                return null;
        }
    }

}