package iammert.com.androidarchitecture.ui.main.science;

import android.view.View;

import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public interface ScienceMovieListCallback {
    void onMovieClicked(ScienceMovieEntity movieEntity, View sharedView);
}
