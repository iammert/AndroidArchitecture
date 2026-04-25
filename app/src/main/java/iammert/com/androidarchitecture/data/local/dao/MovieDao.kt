package iammert.com.androidarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): Flow<MovieEntity?>
}
