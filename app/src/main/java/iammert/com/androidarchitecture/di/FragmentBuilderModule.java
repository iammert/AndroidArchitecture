package iammert.com.androidarchitecture.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import iammert.com.androidarchitecture.ui.main.MovieListFragment;

/**
 * Created by mertsimsek on 30/05/2017.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();
}
