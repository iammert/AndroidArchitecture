package iammert.com.androidarchitecture.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import iammert.com.androidarchitecture.data.model.Movie

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
    posterPath = posterPath,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage.orEmpty(),
    voteAverage = voteAverage,
    voteCount = voteCount,
    isAdult = isAdult
)
