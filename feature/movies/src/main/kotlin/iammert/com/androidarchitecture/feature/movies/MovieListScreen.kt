package iammert.com.androidarchitecture.feature.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import iammert.com.androidarchitecture.feature.movies.R
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import iammert.com.androidarchitecture.core.designsystem.animation.sharedPoster

@Composable
fun MovieListRoute(
    category: MovieCategory,
    onMovieClick: (Movie) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel<MovieListViewModel, MovieListViewModel.Factory>(
        key = category.name
    ) { factory -> factory.create(category) }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MovieListScreen(
        uiState = uiState,
        onMovieClick = onMovieClick,
        onRefresh = viewModel::refresh,
        onErrorShown = viewModel::onErrorShown
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    uiState: MovieListUiState,
    onMovieClick: (Movie) -> Unit,
    onRefresh: () -> Unit,
    onErrorShown: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val hasMovies = uiState.movies.isNotEmpty()

    if (hasMovies && uiState.errorMessage != null) {
        LaunchedEffect(uiState.errorMessage) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
            onErrorShown()
        }
    }

    PullToRefreshBox(
        isRefreshing = hasMovies && uiState.isLoading,
        onRefresh = onRefresh,
        modifier = modifier.fillMaxSize()
    ) {
        when {
            hasMovies -> MovieGrid(uiState.movies, onMovieClick)
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            uiState.errorMessage != null -> ErrorMessage(
                message = uiState.errorMessage,
                onRetry = onRefresh,
                modifier = Modifier.align(Alignment.Center)
            )
            uiState.isEmpty -> Text(
                text = stringResource(R.string.no_movies),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        SnackbarHost(snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun MovieGrid(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies, key = { it.id }) { movie ->
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
                    .sharedPoster(movie.id)
                    .clickable { onMovieClick(movie) }
            )
        }
    }
}

@Composable
private fun ErrorMessage(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = message, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
