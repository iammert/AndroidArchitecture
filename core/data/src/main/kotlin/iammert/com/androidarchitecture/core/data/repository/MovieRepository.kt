package iammert.com.androidarchitecture.core.data.repository

import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun observeMovies(category: MovieCategory): Flow<List<Movie>>

    suspend fun refreshMovies(category: MovieCategory): Result<Unit>

    fun observeMovie(id: Int): Flow<Movie?>
}
