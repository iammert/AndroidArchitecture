package iammert.com.androidarchitecture.ui.detail.popular;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.databinding.ActivityMovieDetailBinding;
import iammert.com.androidarchitecture.enums.MovieType;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private static final String KEY_MOVIE_ID = "key_movie_id";
    private static final String KEY_MOVIE_TYPE = "key_movie_type";

    ActivityMovieDetailBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    MovieDetailViewModel movieDetailViewModel;

    public static Intent newIntent(Context context, int movieId, MovieType movieType) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(KEY_MOVIE_ID, movieId);
        intent.putExtra(KEY_MOVIE_TYPE, movieType);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movieDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel.class);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MovieType movieType = (MovieType) getIntent().getSerializableExtra(KEY_MOVIE_TYPE);

        int movieId = getIntent().getIntExtra(KEY_MOVIE_ID, 0);
        movieDetailViewModel.getMovie(movieId)
                .observe(this, movieEntity -> binding.setMovie(movieEntity));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
