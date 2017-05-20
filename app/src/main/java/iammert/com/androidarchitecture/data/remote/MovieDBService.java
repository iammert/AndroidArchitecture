package iammert.com.androidarchitecture.data.remote;

import iammert.com.androidarchitecture.data.remote.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public interface MovieDBService {

    @GET("movie/popular")
    Call<MoviesResponse> loadMovies();

}
