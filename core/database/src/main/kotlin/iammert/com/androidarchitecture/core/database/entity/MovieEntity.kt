package iammert.com.androidarchitecture.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
