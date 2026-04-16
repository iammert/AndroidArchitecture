package iammert.com.androidarchitecture.data.remote

import iammert.com.androidarchitecture.data.remote.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieDBService {

    @GET("movie/popular")
    fun loadMovies(): Call<MoviesResponse>
}
