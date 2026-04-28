package iammert.com.androidarchitecture.data.remote.model

import iammert.com.androidarchitecture.data.local.entity.MovieEntity
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

fun MovieDto.toEntity() = MovieEntity(
    id = id,
    posterPath = posterPath,
    isAdult = adult,
    overview = overview,
    originalTitle = originalTitle,
    title = title,
    voteCount = voteCount,
    voteAverage = voteAverage,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage
)
