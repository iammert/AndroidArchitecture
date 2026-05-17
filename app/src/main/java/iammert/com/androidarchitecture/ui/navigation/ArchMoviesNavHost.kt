package iammert.com.androidarchitecture.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import iammert.com.androidarchitecture.ui.detail.MovieDetailRoute as MovieDetailScreenRoute
import iammert.com.androidarchitecture.ui.home.HomeScreen

@Composable
fun ArchMoviesNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onMovieClick = { movie -> navController.navigate(MovieDetailRoute(movie.id)) })
        }
        composable<MovieDetailRoute> {
            MovieDetailScreenRoute(onBack = navController::navigateUp)
        }
    }
}
