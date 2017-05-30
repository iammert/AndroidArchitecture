package iammert.com.androidarchitecture.di;

import dagger.Subcomponent;
import iammert.com.androidarchitecture.ui.detail.MovieDetailViewModel;
import iammert.com.androidarchitecture.ui.main.MovieListViewModel;

/**
 * Created by mertsimsek on 30/05/2017.
 */

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }

    MovieListViewModel movieListViewModel();
    MovieDetailViewModel movieDetailViewModel();
}
