package iammert.com.androidarchitecture.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import iammert.com.androidarchitecture.ui.detail.MovieDetailViewModel;
import iammert.com.androidarchitecture.ui.main.MovieListViewModel;
import iammert.com.androidarchitecture.viewmodel.MovieViewModelFactory;

/**
 * Created by mertsimsek on 19/06/2017.
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract ViewModel bindsMovieListViewModel(MovieListViewModel movieListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    abstract  ViewModel bindsMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(MovieViewModelFactory movieViewModelFactory);
}
