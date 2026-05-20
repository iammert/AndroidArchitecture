package iammert.com.androidarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import iammert.com.androidarchitecture.data.local.entity.MovieCategoryEntity
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.data.model.MovieCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query(
        """
        SELECT movies.* FROM movies
        INNER JOIN movie_categories ON movie_categories.movieId = movies.id
        WHERE movie_categories.category = :category
        ORDER BY movie_categories.position
        """
    )
    fun observeMovies(category: MovieCategory): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun observeMovie(id: Int): Flow<MovieEntity?>

    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)

    @Upsert
    suspend fun upsertCategories(categories: List<MovieCategoryEntity>)

    @Query("DELETE FROM movie_categories WHERE category = :category")
    suspend fun clearCategory(category: MovieCategory)

    @Transaction
    suspend fun replaceCategory(category: MovieCategory, movies: List<MovieEntity>) {
        upsertMovies(movies)
        clearCategory(category)
        upsertCategories(movies.mapIndexed { index, movie -> MovieCategoryEntity(category, movie.id, index) })
    }
}
