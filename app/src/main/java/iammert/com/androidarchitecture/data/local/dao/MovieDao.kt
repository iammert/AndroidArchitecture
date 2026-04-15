package iammert.com.androidarchitecture.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): LiveData<MovieEntity>
}
