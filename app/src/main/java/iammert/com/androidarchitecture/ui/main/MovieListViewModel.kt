package iammert.com.androidarchitecture.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val isRefreshing = MutableStateFlow(true)
    private val refreshError = MutableStateFlow<String?>(null)

    val uiState: StateFlow<MovieListUiState> = combine(
        movieRepository.observePopularMovies(),
        isRefreshing,
        refreshError
    ) { movies, refreshing, error ->
        MovieListUiState(movies = movies, isLoading = refreshing, errorMessage = error)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MovieListUiState()
    )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            isRefreshing.value = true
            refreshError.value = movieRepository.refreshPopularMovies().exceptionOrNull()?.message
            isRefreshing.value = false
        }
    }
}
