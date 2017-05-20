package iammert.com.androidarchitecture.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public class MoviesPagerAdapter extends FragmentStatePagerAdapter{

    private static final String[] titles = new String[]{"Popular", "Science", "Comedy"};

    public MoviesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return MovieListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
