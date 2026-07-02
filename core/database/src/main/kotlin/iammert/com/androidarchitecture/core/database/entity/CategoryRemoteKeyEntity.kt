package iammert.com.androidarchitecture.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import iammert.com.androidarchitecture.core.model.MovieCategory

@Entity(tableName = "category_remote_keys")
data class CategoryRemoteKeyEntity(
    @PrimaryKey val category: MovieCategory,
    val nextPage: Int?
)
