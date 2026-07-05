package iammert.com.androidarchitecture.feature.movies

import androidx.paging.testing.asSnapshot
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.testing.FakeMovieRepository
import iammert.com.androidarchitecture.core.testing.MainDispatcherRule
import iammert.com.androidarchitecture.core.testing.testMovie
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MovieListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeMovieRepository().apply {
        setMovies(MovieCategory.POPULAR, listOf(testMovie(1), testMovie(2)))
        setMovies(MovieCategory.COMEDY, listOf(testMovie(3)))
    }

    @Test
    fun showsTheMoviesOfItsCategory() = runTest {
        val viewModel = MovieListViewModel(MovieCategory.COMEDY, repository)

        assertEquals(listOf(testMovie(3)), viewModel.movies.asSnapshot())
    }

    @Test
    fun categoriesDoNotShareMovies() = runTest {
        val viewModel = MovieListViewModel(MovieCategory.POPULAR, repository)

        assertEquals(listOf(1, 2), viewModel.movies.asSnapshot().map { it.id })
    }
}
