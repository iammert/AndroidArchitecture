package iammert.com.androidarchitecture.core.data.repository

import androidx.paging.PagingData
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun pagedMovies(category: MovieCategory): Flow<PagingData<Movie>>

    fun observeMovie(id: Int): Flow<Movie?>
}
