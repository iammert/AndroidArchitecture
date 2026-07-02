package iammert.com.androidarchitecture.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import iammert.com.androidarchitecture.core.data.asExternalModel
import iammert.com.androidarchitecture.core.database.dao.MovieDao
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.network.MovieDBService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Offline-first: pages are always read from the database, the network only fills it.
 */
internal class OfflineFirstMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun pagedMovies(category: MovieCategory): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = MovieRemoteMediator(category, movieDao, movieDBService),
        pagingSourceFactory = { movieDao.pagingSource(category) }
    ).flow.map { pagingData -> pagingData.map { it.asExternalModel() } }

    override fun observeMovie(id: Int): Flow<Movie?> = movieDao.observeMovie(id).map { it?.asExternalModel() }

    private companion object {
        const val PAGE_SIZE = 20
    }
}
