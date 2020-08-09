package iammert.com.androidarchitecture.ui.detail.science;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class MovieScienceDetailViewModel extends ViewModel {
    private final LiveData<Resource<ScienceMovieEntity>> movieDetail = new MutableLiveData<>();
    private final MovieRepository movieRepository;

    @Inject
    public MovieScienceDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<ScienceMovieEntity> getScienceMovie(int id) {
        return movieRepository.getScienceMovie(id);
    }
}
