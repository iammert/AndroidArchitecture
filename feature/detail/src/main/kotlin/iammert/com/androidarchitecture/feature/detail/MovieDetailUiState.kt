package iammert.com.androidarchitecture.feature.detail

import iammert.com.androidarchitecture.core.model.Movie

sealed interface MovieDetailUiState {
    data object Loading : MovieDetailUiState
    data object NotFound : MovieDetailUiState
    data class Success(val movie: Movie) : MovieDetailUiState
}
