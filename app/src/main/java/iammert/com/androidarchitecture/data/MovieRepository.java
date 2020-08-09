package iammert.com.androidarchitecture.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import iammert.com.androidarchitecture.data.local.dao.MovieDao;
import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;
import iammert.com.androidarchitecture.data.remote.ApiConstants;
import iammert.com.androidarchitecture.data.remote.MovieDBService;
import iammert.com.androidarchitecture.data.remote.model.ComedyMoviesResponse;
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse;
import iammert.com.androidarchitecture.data.remote.model.ScienceMoviesResponse;
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

    public LiveData<Resource<List<ComedyMovieEntity>>> loadComedyMovies() {
        return new NetworkBoundResource<List<ComedyMovieEntity>, ComedyMoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull ComedyMoviesResponse item) {
                movieDao.saveComedyMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<ComedyMovieEntity>> loadFromDb() {
                return movieDao.loadComedyMovies();
            }

            @NonNull
            @Override
            protected Call<ComedyMoviesResponse> createCall() {
                return movieDBService.loadComedyMoviesByGenre(ApiConstants.COMEDY_GENRE_ID);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<ScienceMovieEntity>>> loadScienceMovies() {
        return new NetworkBoundResource<List<ScienceMovieEntity>, ScienceMoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull ScienceMoviesResponse item) {
                movieDao.saveScienceMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<ScienceMovieEntity>> loadFromDb() {
                return movieDao.loadScienceMovie();
            }

            @NonNull
            @Override
            protected Call<ScienceMoviesResponse> createCall() {
                return movieDBService.loadScienceMoviesByGenre(ApiConstants.SCIENCE_GENRE_ID);
            }
        }.getAsLiveData();
    }

    public LiveData<MovieEntity> getMovie(int id) {
        return movieDao.getMovie(id);
    }

    public LiveData<ComedyMovieEntity> getComedyMovie(int id) {
        return movieDao.getComedyMovie(id);
    }

    public LiveData<ScienceMovieEntity> getScienceMovie(int id) {
        return movieDao.getScienceMovie(id);
    }
}
