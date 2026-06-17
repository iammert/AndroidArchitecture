package iammert.com.androidarchitecture.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import iammert.com.androidarchitecture.core.database.dao.MovieDao
import iammert.com.androidarchitecture.core.database.entity.MovieCategoryEntity
import iammert.com.androidarchitecture.core.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieCategoryEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
