package iammert.com.androidarchitecture.data

import iammert.com.androidarchitecture.core.database.entity.MovieEntity
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.network.ApiConstants
import iammert.com.androidarchitecture.core.network.model.MovieDto

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

fun MovieEntity.asExternalModel() = Movie(
    id = id,
    title = title.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    posterUrl = posterPath?.let { ApiConstants.IMAGE_ENDPOINT_PREFIX + it },
    backdropUrl = backdropPath?.let { ApiConstants.IMAGE_ENDPOINT_PREFIX + it },
    originalLanguage = originalLanguage.orEmpty(),
    voteAverage = voteAverage,
    voteCount = voteCount,
    isAdult = isAdult
)
