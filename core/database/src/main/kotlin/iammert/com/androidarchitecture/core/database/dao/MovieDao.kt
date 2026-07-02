package iammert.com.androidarchitecture.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import iammert.com.androidarchitecture.core.database.entity.CategoryRemoteKeyEntity
import iammert.com.androidarchitecture.core.database.entity.MovieCategoryEntity
import iammert.com.androidarchitecture.core.database.entity.MovieEntity
import iammert.com.androidarchitecture.core.model.MovieCategory
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
    fun pagingSource(category: MovieCategory): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun observeMovie(id: Int): Flow<MovieEntity?>

    @Query("SELECT * FROM category_remote_keys WHERE category = :category")
    suspend fun remoteKey(category: MovieCategory): CategoryRemoteKeyEntity?

    @Query("SELECT COUNT(*) FROM movie_categories WHERE category = :category")
    suspend fun movieCount(category: MovieCategory): Int

    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)

    @Upsert
    suspend fun upsertCategories(categories: List<MovieCategoryEntity>)

    @Upsert
    suspend fun upsertRemoteKey(remoteKey: CategoryRemoteKeyEntity)

    @Query("DELETE FROM movie_categories WHERE category = :category")
    suspend fun clearCategory(category: MovieCategory)

    @Transaction
    suspend fun insertPage(
        category: MovieCategory,
        movies: List<MovieEntity>,
        nextPage: Int?,
        clearFirst: Boolean
    ) {
        if (clearFirst) {
            clearCategory(category)
        }
        val offset = movieCount(category)
        upsertMovies(movies)
        upsertCategories(movies.mapIndexed { index, movie -> MovieCategoryEntity(category, movie.id, offset + index) })
        upsertRemoteKey(CategoryRemoteKeyEntity(category, nextPage))
    }
}
