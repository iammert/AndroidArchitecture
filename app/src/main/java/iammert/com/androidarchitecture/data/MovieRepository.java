package iammert.com.androidarchitecture.data;

import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.local.dao.MovieDao;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;
import iammert.com.androidarchitecture.data.remote.MovieDBService;
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse;
import retrofit2.Call;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MovieRepository {

    private final MovieDao movieDao;
    private final MovieDBService movieDBService;

    @Inject
    public MovieRepository(MovieDao movieDao, MovieDBService movieDBService) {
        this.movieDao = movieDao;
        this.movieDBService = movieDBService;
    }

    public LiveData<Resource<List<MovieEntity>>> loadPopularMovies() {
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return movieDBService.loadMovies();
            }
        }.getAsLiveData();
    }

    public LiveData<MovieEntity> getMovie(int id){
        return movieDao.getMovie(id);
    }
}
