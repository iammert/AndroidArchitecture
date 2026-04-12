package iammert.com.androidarchitecture.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val isAdult: Boolean = false,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null
)
