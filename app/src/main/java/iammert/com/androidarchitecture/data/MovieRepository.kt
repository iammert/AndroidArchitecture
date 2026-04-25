package iammert.com.androidarchitecture.data

import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.data.remote.MovieDBService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Offline-first: the database is the single source of truth, the network only refreshes it.
 */
class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) {

    fun observePopularMovies(): Flow<List<MovieEntity>> = movieDao.loadMovies()

    suspend fun refreshPopularMovies(): Result<Unit> = try {
        movieDao.saveMovies(movieDBService.loadMovies().results)
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    fun observeMovie(id: Int): Flow<MovieEntity?> = movieDao.getMovie(id)
}
