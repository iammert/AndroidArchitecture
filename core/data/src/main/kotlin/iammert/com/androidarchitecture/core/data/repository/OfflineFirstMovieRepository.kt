package iammert.com.androidarchitecture.core.data.repository

import iammert.com.androidarchitecture.core.database.dao.MovieDao
import iammert.com.androidarchitecture.core.data.asExternalModel
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.network.MovieDBService
import iammert.com.androidarchitecture.core.network.model.MoviesResponse
import iammert.com.androidarchitecture.core.data.toEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Offline-first: the database is the single source of truth, the network only refreshes it.
 */
internal class OfflineFirstMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) : MovieRepository {

    override fun observeMovies(category: MovieCategory): Flow<List<Movie>> =
        movieDao.observeMovies(category).map { movies -> movies.map { it.asExternalModel() } }

    override suspend fun refreshMovies(category: MovieCategory): Result<Unit> = try {
        val movies = fetch(category).results.map { it.toEntity() }
        movieDao.replaceCategory(category, movies)
        Result.success(Unit)
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    override fun observeMovie(id: Int): Flow<Movie?> = movieDao.observeMovie(id).map { it?.asExternalModel() }

    private suspend fun fetch(category: MovieCategory): MoviesResponse = when (category) {
        MovieCategory.POPULAR -> movieDBService.popularMovies()
        MovieCategory.SCIENCE_FICTION -> movieDBService.discoverMovies(GENRE_SCIENCE_FICTION)
        MovieCategory.COMEDY -> movieDBService.discoverMovies(GENRE_COMEDY)
    }

    private companion object {
        const val GENRE_SCIENCE_FICTION = 878
        const val GENRE_COMEDY = 35
    }
}
