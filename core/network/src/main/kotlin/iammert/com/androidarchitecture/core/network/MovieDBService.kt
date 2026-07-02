package iammert.com.androidarchitecture.core.network

import iammert.com.androidarchitecture.core.network.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/popular")
    suspend fun popularMovies(@Query("page") page: Int): MoviesResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MoviesResponse
}
