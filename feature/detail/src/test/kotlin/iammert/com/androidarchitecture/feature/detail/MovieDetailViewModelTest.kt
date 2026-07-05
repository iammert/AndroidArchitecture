package iammert.com.androidarchitecture.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.testing.invoke
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.testing.FakeMovieRepository
import iammert.com.androidarchitecture.core.testing.MainDispatcherRule
import iammert.com.androidarchitecture.core.testing.testMovie
import iammert.com.androidarchitecture.feature.detail.navigation.MovieDetailRoute
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeMovieRepository()

    private fun viewModel(movieId: Int) = MovieDetailViewModel(
        savedStateHandle = SavedStateHandle(route = MovieDetailRoute(movieId)),
        movieRepository = repository
    )

    @Test
    fun showsTheMovieOfTheRoute() = runTest {
        repository.setMovies(MovieCategory.POPULAR, listOf(testMovie(1), testMovie(2)))

        viewModel(movieId = 2).uiState.test {
            assertEquals(MovieDetailUiState.Success(testMovie(2)), awaitItem())
        }
    }

    @Test
    fun unknownMovieIsNotFound() = runTest {
        repository.setMovies(MovieCategory.POPULAR, listOf(testMovie(1)))

        viewModel(movieId = 42).uiState.test {
            assertEquals(MovieDetailUiState.NotFound, awaitItem())
        }
    }

    @Test
    fun updatesWhenTheMovieChanges() = runTest {
        repository.setMovies(MovieCategory.POPULAR, listOf(testMovie(1, title = "Old")))

        viewModel(movieId = 1).uiState.test {
            assertEquals(MovieDetailUiState.Success(testMovie(1, title = "Old")), awaitItem())

            repository.setMovies(MovieCategory.POPULAR, listOf(testMovie(1, title = "New")))

            assertEquals(MovieDetailUiState.Success(testMovie(1, title = "New")), awaitItem())
        }
    }
}
