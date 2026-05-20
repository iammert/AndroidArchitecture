package iammert.com.androidarchitecture.data.remote

import iammert.com.androidarchitecture.data.remote.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {

    @GET("movie/popular")
    suspend fun popularMovies(): MoviesResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): MoviesResponse
}
