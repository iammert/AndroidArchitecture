package iammert.com.androidarchitecture.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.core.model.MovieCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MovieListViewModel.Factory::class)
class MovieListViewModel @AssistedInject constructor(
    @Assisted private val category: MovieCategory,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val isRefreshing = MutableStateFlow(true)
    private val refreshError = MutableStateFlow<String?>(null)

    val uiState: StateFlow<MovieListUiState> = combine(
        movieRepository.observeMovies(category),
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
            refreshError.value = movieRepository.refreshMovies(category).exceptionOrNull()?.message
            isRefreshing.value = false
        }
    }

    fun onErrorShown() {
        refreshError.value = null
    }

    @AssistedFactory
    interface Factory {
        fun create(category: MovieCategory): MovieListViewModel
    }
}
