package iammert.com.androidarchitecture.feature.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import iammert.com.androidarchitecture.core.data.repository.MovieRepository
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

@HiltViewModel(assistedFactory = MovieListViewModel.Factory::class)
class MovieListViewModel @AssistedInject constructor(
    @Assisted category: MovieCategory,
    movieRepository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = movieRepository.pagedMovies(category).cachedIn(viewModelScope)

    @AssistedFactory
    interface Factory {
        fun create(category: MovieCategory): MovieListViewModel
    }
}
