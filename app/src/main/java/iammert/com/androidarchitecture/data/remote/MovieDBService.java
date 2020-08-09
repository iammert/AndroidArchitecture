package iammert.com.androidarchitecture.data.remote;

import iammert.com.androidarchitecture.data.remote.model.ComedyMoviesResponse;
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse;
import iammert.com.androidarchitecture.data.remote.model.ScienceMoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public interface MovieDBService {

    @GET("movie/popular")
    Call<MoviesResponse> loadMovies();

    @GET("discover/movie")
    Call<ScienceMoviesResponse> loadScienceMoviesByGenre(@Query("with_genres") String genreId);

    @GET("discover/movie")
    Call<ComedyMoviesResponse> loadComedyMoviesByGenre(@Query("with_genres") String genreId);

}
