package iammert.com.androidarchitecture.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.data.model.Movie
import iammert.com.androidarchitecture.data.remote.ApiConstants
import iammert.com.androidarchitecture.ui.common.sharedPoster
import java.util.Locale

@Composable
fun MovieDetailRoute(
    onBack: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    MovieDetailScreen(uiState = uiState, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(uiState: MovieDetailUiState, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        when (uiState) {
            MovieDetailUiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            MovieDetailUiState.NotFound -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.movie_not_found))
            }
            is MovieDetailUiState.Success -> MovieDetail(movie = uiState.movie, bottomPadding = padding.calculateBottomPadding())
        }
    }
}

@Composable
private fun MovieDetail(movie: Movie, bottomPadding: Dp) {
    val locale = ConfigurationCompat.getLocales(LocalConfiguration.current)[0] ?: Locale.ROOT
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = bottomPadding)
    ) {
        AsyncImage(
            model = (movie.backdropPath ?: movie.posterPath)?.let { ApiConstants.IMAGE_ENDPOINT_PREFIX + it },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = movie.posterPath?.let { ApiConstants.IMAGE_ENDPOINT_PREFIX + it },
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(y = (-48).dp)
                    .width(110.dp)
                    .aspectRatio(2f / 3f)
                    .sharedPoster(movie.id)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(top = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(movie.title, style = MaterialTheme.typography.titleLarge)
                if (movie.originalTitle != movie.title) {
                    Text(movie.originalTitle, style = MaterialTheme.typography.bodyMedium)
                }
                MovieInfo(
                    icon = Icons.Filled.Star,
                    text = stringResource(
                        R.string.rating,
                        String.format(locale, "%.1f", movie.voteAverage),
                        movie.voteCount
                    )
                )
                MovieInfo(icon = Icons.Filled.Language, text = movie.originalLanguage.uppercase(Locale.ROOT))
            }
        }
        Text(
            text = stringResource(R.string.overview),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun MovieInfo(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary, modifier = Modifier.size(18.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}
