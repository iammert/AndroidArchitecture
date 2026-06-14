package iammert.com.androidarchitecture.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    @SerialName("poster_path") val posterPath: String? = null,
    val adult: Boolean = false,
    val overview: String? = null,
    @SerialName("original_title") val originalTitle: String? = null,
    val title: String? = null,
    @SerialName("vote_count") val voteCount: Int = 0,
    @SerialName("vote_average") val voteAverage: Double = 0.0,
    @SerialName("backdrop_path") val backdropPath: String? = null,
    @SerialName("original_language") val originalLanguage: String? = null
)
