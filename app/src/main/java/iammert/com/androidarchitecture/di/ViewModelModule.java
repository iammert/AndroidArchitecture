package iammert.com.androidarchitecture.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import iammert.com.androidarchitecture.ui.detail.comedy.MovieComedyDetailViewModel;
import iammert.com.androidarchitecture.ui.detail.popular.MovieDetailViewModel;
import iammert.com.androidarchitecture.ui.detail.science.MovieScienceDetailViewModel;
import iammert.com.androidarchitecture.ui.main.MovieListViewModel;
import iammert.com.androidarchitecture.ui.main.comedy.MovieComedyListViewModel;
import iammert.com.androidarchitecture.ui.main.science.MovieScienceListViewModel;
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
    @ViewModelKey(MovieComedyListViewModel.class)
    abstract ViewModel bindsMovieComedyListViewModel(MovieComedyListViewModel movieListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieScienceListViewModel.class)
    abstract ViewModel bindsMovieScienceListViewModel(MovieScienceListViewModel movieListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    abstract ViewModel bindsMovieDetailViewModel(MovieDetailViewModel movieDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieComedyDetailViewModel.class)
    abstract ViewModel bindsMovieComedyDetailViewModel(MovieComedyDetailViewModel movieDetailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieScienceDetailViewModel.class)
    abstract ViewModel bindsMovieScienceDetailViewModel(MovieScienceDetailViewModel movieDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(MovieViewModelFactory movieViewModelFactory);
}
