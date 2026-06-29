package iammert.com.androidarchitecture.feature.detail.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import iammert.com.androidarchitecture.core.designsystem.animation.LocalAnimatedVisibilityScope
import iammert.com.androidarchitecture.feature.detail.MovieDetailRoute as MovieDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailRoute(val movieId: Int)

fun NavController.navigateToMovieDetail(movieId: Int) = navigate(MovieDetailRoute(movieId))

fun NavGraphBuilder.movieDetailScreen(onBack: () -> Unit) {
    composable<MovieDetailRoute> {
        CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
            MovieDetailScreen(onBack = onBack)
        }
    }
}
