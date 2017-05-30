package iammert.com.androidarchitecture.ui.main;

import android.view.View;

import iammert.com.androidarchitecture.data.local.entity.MovieEntity;

/**
 * Created by mertsimsek on 21/05/2017.
 */

public interface MovieListCallback {
    void onMovieClicked(MovieEntity movieEntity, View sharedView);
}
