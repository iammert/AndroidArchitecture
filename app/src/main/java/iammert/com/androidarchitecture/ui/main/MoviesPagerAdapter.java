package iammert.com.androidarchitecture.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import iammert.com.androidarchitecture.ui.main.comedy.MovieComedyListFragment;
import iammert.com.androidarchitecture.ui.main.science.MovieScienceListFragment;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public class MoviesPagerAdapter extends FragmentStatePagerAdapter {

    private static final String[] titles = new String[]{"Popular", "Science", "Comedy"};

    public MoviesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return MovieListFragment.newInstance();
        } else if (i == 1) {
            return MovieScienceListFragment.newInstance();
        } else if (i == 2) {
            return MovieComedyListFragment.newInstance();
        } else {
            return MovieListFragment.newInstance();
        }
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
