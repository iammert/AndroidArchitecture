package iammert.com.androidarchitecture.ui.main.science;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class MovieScienceListViewModel extends ViewModel {
    private final LiveData<Resource<List<ScienceMovieEntity>>> scienceMovies;

    @Inject
    public MovieScienceListViewModel(MovieRepository movieRepository) {
        scienceMovies = movieRepository.loadScienceMovies();
    }

    LiveData<Resource<List<ScienceMovieEntity>>> getScienceMovies() {
        return scienceMovies;
    }
}
