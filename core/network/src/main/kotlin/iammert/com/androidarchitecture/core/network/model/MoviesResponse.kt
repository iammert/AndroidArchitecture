package iammert.com.androidarchitecture.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(val results: List<MovieDto> = emptyList())
