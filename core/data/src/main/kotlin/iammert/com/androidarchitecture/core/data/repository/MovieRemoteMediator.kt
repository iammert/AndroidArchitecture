package iammert.com.androidarchitecture.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import iammert.com.androidarchitecture.core.data.toEntity
import iammert.com.androidarchitecture.core.database.dao.MovieDao
import iammert.com.androidarchitecture.core.database.entity.MovieEntity
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.network.MovieDBService
import iammert.com.androidarchitecture.core.network.model.MoviesResponse
import kotlinx.coroutines.CancellationException

@OptIn(ExperimentalPagingApi::class)
internal class MovieRemoteMediator(
    private val category: MovieCategory,
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> FIRST_PAGE
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> movieDao.remoteKey(category)?.nextPage
                ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        return try {
            val response = fetch(page)
            val nextPage = if (response.page < response.totalPages && response.results.isNotEmpty()) page + 1 else null
            movieDao.insertPage(
                category = category,
                movies = response.results.map { it.toEntity() },
                nextPage = nextPage,
                clearFirst = loadType == LoadType.REFRESH
            )
            MediatorResult.Success(endOfPaginationReached = nextPage == null)
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun fetch(page: Int): MoviesResponse = when (category) {
        MovieCategory.POPULAR -> movieDBService.popularMovies(page)
        MovieCategory.SCIENCE_FICTION -> movieDBService.discoverMovies(GENRE_SCIENCE_FICTION, page)
        MovieCategory.COMEDY -> movieDBService.discoverMovies(GENRE_COMEDY, page)
    }

    private companion object {
        const val FIRST_PAGE = 1
        const val GENRE_SCIENCE_FICTION = 878
        const val GENRE_COMEDY = 35
    }
}
