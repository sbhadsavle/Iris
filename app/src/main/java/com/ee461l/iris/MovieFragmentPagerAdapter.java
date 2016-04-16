package com.ee461l.iris;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by saran on 3/31/2016.
 */
public class MovieFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Exclusive Content", "Trailers", "Showtimes" };
    private Context context;

    public MovieFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return ExclusiveContentFragment.newInstance(position + 1);
        }
        if(position == 1) {
            return TrailersFragment.newInstance(position + 1);
        }
        if (position == 2){
            //return MovieMapFragment.newInstance(position + 1);
            return ShowtimesFragment.newInstance(position + 1);
        }
        return SampleFragment.newInstance(position + 1);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
