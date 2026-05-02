package iammert.com.androidarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.Resource
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val refreshError = MutableStateFlow<String?>(null)
    private val isRefreshing = MutableStateFlow(true)

    val popularMovies: LiveData<Resource<List<MovieEntity>>> = combine(
        movieRepository.observePopularMovies(),
        isRefreshing,
        refreshError
    ) { movies, refreshing, error ->
        when {
            refreshing -> Resource.loading(movies)
            error != null -> Resource.error(error, movies)
            else -> Resource.success(movies)
        }
    }.asLiveData()

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
