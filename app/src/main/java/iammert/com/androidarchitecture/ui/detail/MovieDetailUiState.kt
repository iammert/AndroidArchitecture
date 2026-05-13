package iammert.com.androidarchitecture.ui.detail

import iammert.com.androidarchitecture.data.model.Movie

sealed interface MovieDetailUiState {
    data object Loading : MovieDetailUiState
    data object NotFound : MovieDetailUiState
    data class Success(val movie: Movie) : MovieDetailUiState
}
