package iammert.com.androidarchitecture.feature.movies

import iammert.com.androidarchitecture.core.model.Movie

data class MovieListUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
) {
    val isEmpty: Boolean get() = movies.isEmpty() && !isLoading && errorMessage == null
}
