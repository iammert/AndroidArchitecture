package iammert.com.androidarchitecture.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import iammert.com.androidarchitecture.core.designsystem.animation.LocalAnimatedVisibilityScope
import iammert.com.androidarchitecture.core.designsystem.animation.LocalSharedTransitionScope
import iammert.com.androidarchitecture.ui.detail.MovieDetailRoute as MovieDetailScreenRoute
import iammert.com.androidarchitecture.feature.movies.navigation.HomeRoute
import iammert.com.androidarchitecture.feature.movies.navigation.homeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArchMoviesNavHost() {
    val navController = rememberNavController()
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(navController = navController, startDestination = HomeRoute) {
                homeScreen(onMovieClick = { movieId -> navController.navigate(MovieDetailRoute(movieId)) })
                composable<MovieDetailRoute> {
                    CompositionLocalProvider(LocalAnimatedVisibilityScope provides this) {
                        MovieDetailScreenRoute(onBack = navController::navigateUp)
                    }
                }
            }
        }
    }
}
