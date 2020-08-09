package iammert.com.androidarchitecture.ui.main.comedy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MovieComedyListViewModel extends ViewModel {
    private final LiveData<Resource<List<ComedyMovieEntity>>> comedyMovies;

    @Inject
    public MovieComedyListViewModel(MovieRepository movieRepository) {
        comedyMovies = movieRepository.loadComedyMovies();
    }

    LiveData<Resource<List<ComedyMovieEntity>>> getComedyMovies() {
        return comedyMovies;
    }
}
