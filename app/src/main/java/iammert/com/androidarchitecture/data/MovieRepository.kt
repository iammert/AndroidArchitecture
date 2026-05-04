package iammert.com.androidarchitecture.data

import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.asExternalModel
import iammert.com.androidarchitecture.data.model.Movie
import iammert.com.androidarchitecture.data.remote.MovieDBService
import iammert.com.androidarchitecture.data.remote.model.toEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Offline-first: the database is the single source of truth, the network only refreshes it.
 */
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) {

    fun observePopularMovies(): Flow<List<Movie>> =
        movieDao.loadMovies().map { movies -> movies.map { it.asExternalModel() } }

    suspend fun refreshPopularMovies(): Result<Unit> = try {
        movieDao.saveMovies(movieDBService.loadMovies().results.map { it.toEntity() })
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    fun observeMovie(id: Int): Flow<Movie?> = movieDao.getMovie(id).map { it?.asExternalModel() }
}
