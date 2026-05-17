package iammert.com.androidarchitecture.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class MovieDetailRoute(val movieId: Int)
