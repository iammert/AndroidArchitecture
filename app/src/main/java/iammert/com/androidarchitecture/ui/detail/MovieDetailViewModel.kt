package iammert.com.androidarchitecture.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    movieRepository: MovieRepository
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[KEY_MOVIE_ID])

    val uiState: StateFlow<MovieDetailUiState> = movieRepository.observeMovie(movieId)
        .map { movie -> movie?.let { MovieDetailUiState.Success(it) } ?: MovieDetailUiState.NotFound }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailUiState.Loading
        )

    companion object {
        const val KEY_MOVIE_ID = "movie_id"
    }
}
