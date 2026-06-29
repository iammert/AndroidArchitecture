package iammert.com.androidarchitecture.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import iammert.com.androidarchitecture.core.designsystem.animation.LocalSharedTransitionScope
import iammert.com.androidarchitecture.feature.detail.navigation.movieDetailScreen
import iammert.com.androidarchitecture.feature.detail.navigation.navigateToMovieDetail
import iammert.com.androidarchitecture.feature.movies.navigation.HomeRoute
import iammert.com.androidarchitecture.feature.movies.navigation.homeScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArchMoviesNavHost() {
    val navController = rememberNavController()
    SharedTransitionLayout {
        CompositionLocalProvider(LocalSharedTransitionScope provides this) {
            NavHost(navController = navController, startDestination = HomeRoute) {
                homeScreen(onMovieClick = navController::navigateToMovieDetail)
                movieDetailScreen(onBack = navController::navigateUp)
            }
        }
    }
}
