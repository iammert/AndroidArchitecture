package iammert.com.androidarchitecture.core.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val originalLanguage: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isAdult: Boolean
)
