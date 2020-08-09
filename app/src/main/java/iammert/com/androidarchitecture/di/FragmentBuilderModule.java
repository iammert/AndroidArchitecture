package iammert.com.androidarchitecture.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import iammert.com.androidarchitecture.ui.main.MovieListFragment;
import iammert.com.androidarchitecture.ui.main.comedy.MovieComedyListFragment;
import iammert.com.androidarchitecture.ui.main.science.MovieScienceListFragment;

/**
 * Created by mertsimsek on 30/05/2017.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract MovieComedyListFragment contributeMovieComedyListFragment();

    @ContributesAndroidInjector
    abstract MovieScienceListFragment contributeMovieScienceListFragment();
}
