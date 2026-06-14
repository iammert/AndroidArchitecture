package iammert.com.androidarchitecture.data

import iammert.com.androidarchitecture.core.network.model.MovieDto
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

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
