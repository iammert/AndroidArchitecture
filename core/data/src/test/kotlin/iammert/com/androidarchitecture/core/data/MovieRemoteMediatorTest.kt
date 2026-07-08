package iammert.com.androidarchitecture.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import iammert.com.androidarchitecture.core.data.repository.MovieRemoteMediator
import iammert.com.androidarchitecture.core.database.MovieDatabase
import iammert.com.androidarchitecture.core.database.entity.MovieEntity
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.network.MovieDBService
import iammert.com.androidarchitecture.core.network.model.MovieDto
import iammert.com.androidarchitecture.core.network.model.MoviesResponse
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
class MovieRemoteMediatorTest {

    private lateinit var database: MovieDatabase
    private val service = FakeMovieDBService()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() = database.close()

    private fun mediator(category: MovieCategory = MovieCategory.POPULAR) =
        MovieRemoteMediator(category, database.movieDao(), service)

    private val emptyState = PagingState<Int, MovieEntity>(emptyList(), null, PagingConfig(20), 0)

    @Test
    fun refreshStoresTheFirstPage() = runTest {
        service.pages = listOf(listOf(1, 2), listOf(3))

        val result = mediator().load(LoadType.REFRESH, emptyState)

        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertEquals(2, database.movieDao().movieCount(MovieCategory.POPULAR))
        assertEquals(2, database.movieDao().remoteKey(MovieCategory.POPULAR)?.nextPage)
    }

    @Test
    fun appendStopsAfterTheLastPage() = runTest {
        service.pages = listOf(listOf(1, 2), listOf(3))
        val mediator = mediator()

        mediator.load(LoadType.REFRESH, emptyState)
        val result = mediator.load(LoadType.APPEND, emptyState)

        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        assertEquals(3, database.movieDao().movieCount(MovieCategory.POPULAR))
        assertNull(database.movieDao().remoteKey(MovieCategory.POPULAR)?.nextPage)
    }

    @Test
    fun refreshReplacesOnlyItsOwnCategory() = runTest {
        service.pages = listOf(listOf(1, 2))
        mediator(MovieCategory.POPULAR).load(LoadType.REFRESH, emptyState)
        mediator(MovieCategory.COMEDY).load(LoadType.REFRESH, emptyState)

        service.pages = listOf(listOf(5))
        mediator(MovieCategory.COMEDY).load(LoadType.REFRESH, emptyState)

        assertEquals(2, database.movieDao().movieCount(MovieCategory.POPULAR))
        assertEquals(1, database.movieDao().movieCount(MovieCategory.COMEDY))
    }

    @Test
    fun networkErrorsAreReported() = runTest {
        service.error = IOException("offline")

        val result = mediator().load(LoadType.REFRESH, emptyState)

        assertEquals("offline", (result as RemoteMediator.MediatorResult.Error).throwable.message)
    }

    private class FakeMovieDBService : MovieDBService {
        var pages: List<List<Int>> = emptyList()
        var error: Exception? = null

        override suspend fun popularMovies(page: Int) = response(page)

        override suspend fun discoverMovies(genreId: Int, page: Int, sortBy: String) = response(page)

        private fun response(page: Int): MoviesResponse {
            error?.let { throw it }
            return MoviesResponse(
                page = page,
                results = pages.getOrElse(page - 1) { emptyList() }.map { MovieDto(id = it, title = "Movie $it") },
                totalPages = pages.size
            )
        }
    }
}
