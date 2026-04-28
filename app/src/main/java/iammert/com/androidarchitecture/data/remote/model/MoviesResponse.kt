package iammert.com.androidarchitecture.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(val results: List<MovieDto> = emptyList())
