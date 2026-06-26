package iammert.com.androidarchitecture.feature.movies.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import iammert.com.androidarchitecture.core.designsystem.animation.LocalAnimatedVisibilityScope
import iammert.com.androidarchitecture.feature.movies.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen(onMovieClick: (movieId: Int) -> Unit) {
    composable<HomeRoute> {
        CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
            HomeScreen(onMovieClick = { movie -> onMovieClick(movie.id) })
        }
    }
}
