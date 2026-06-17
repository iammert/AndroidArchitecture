package iammert.com.androidarchitecture.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import iammert.com.androidarchitecture.core.model.MovieCategory

@Entity(
    tableName = "movie_categories",
    primaryKeys = ["category", "movieId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("movieId")]
)
data class MovieCategoryEntity(
    val category: MovieCategory,
    val movieId: Int,
    val position: Int
)
