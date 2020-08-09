package iammert.com.androidarchitecture.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import iammert.com.androidarchitecture.ui.detail.comedy.MovieComedyDetailActivity;
import iammert.com.androidarchitecture.ui.detail.popular.MovieDetailActivity;
import iammert.com.androidarchitecture.ui.detail.science.MovieScienceDetailActivity;
import iammert.com.androidarchitecture.ui.main.MainActivity;

/**
 * Created by mertsimsek on 30/05/2017.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MovieDetailActivity movieDetailActivity();

    @ContributesAndroidInjector
    abstract MovieComedyDetailActivity movieComedyDetailActivity();

    @ContributesAndroidInjector
    abstract MovieScienceDetailActivity movieScienceDetailActivity();
}
