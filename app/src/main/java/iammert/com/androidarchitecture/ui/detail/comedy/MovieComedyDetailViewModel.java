package iammert.com.androidarchitecture.ui.detail.comedy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;

/**
 * Created by mertsimsek on 21/05/2017.
 */

public class MovieComedyDetailViewModel extends ViewModel {
    private final LiveData<Resource<ComedyMovieEntity>> movieDetail = new MutableLiveData<>();
    private final MovieRepository movieRepository;

    @Inject
    public MovieComedyDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<ComedyMovieEntity> getComedyMovie(int id) {
        return movieRepository.getComedyMovie(id);
    }
}
