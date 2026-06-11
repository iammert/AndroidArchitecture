package iammert.com.androidarchitecture.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.data.remote.ApiConstants

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int = 0,
    val posterPath: String? = null,
    @ColumnInfo(name = "adult")
    val isAdult: Boolean = false,
    val overview: String? = null,
    val originalTitle: String? = null,
    val title: String? = null,
    val voteCount: Int = 0,
    val voteAverage: Double = 0.0,
    val backdropPath: String? = null,
    val originalLanguage: String? = null
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
