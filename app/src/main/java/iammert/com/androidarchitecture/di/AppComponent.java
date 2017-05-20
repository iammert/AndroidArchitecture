package iammert.com.androidarchitecture.di;

import javax.inject.Singleton;

import dagger.Component;
import iammert.com.androidarchitecture.ui.detail.MovieDetailActivity;
import iammert.com.androidarchitecture.ui.main.MainActivity;
import iammert.com.androidarchitecture.ui.main.MovieListFragment;

/**
 * Created by mertsimsek on 20/05/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MovieListFragment movieListFragment);
    void inject(MovieDetailActivity detailActivity);
}
