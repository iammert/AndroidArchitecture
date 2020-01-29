package iammert.com.androidarchitecture.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;

/**
 * Created by mertsimsek on 21/05/2017.
 */

public class MovieDetailViewModel extends ViewModel{
    private final LiveData<Resource<MovieEntity>>  movieDetail = new MutableLiveData<>();
    private final MovieRepository movieRepository;

    @Inject
    public MovieDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MovieEntity> getMovie(int id){
        return movieRepository.getMovie(id);
    }
}
