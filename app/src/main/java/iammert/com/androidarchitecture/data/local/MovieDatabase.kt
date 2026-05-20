package iammert.com.androidarchitecture.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieCategoryEntity
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieCategoryEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
