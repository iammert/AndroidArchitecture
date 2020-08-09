package iammert.com.androidarchitecture.ui.main.comedy;

import android.view.View;

import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;

/**
 * Created by mertsimsek on 21/05/2017.
 */

public interface ComedyMovieListCallback {
    void onMovieClicked(ComedyMovieEntity movieEntity, View sharedView);
}
