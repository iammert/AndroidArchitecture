package iammert.com.androidarchitecture.core.testing

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import iammert.com.androidarchitecture.core.data.repository.MovieRepository
import iammert.com.androidarchitecture.core.model.Movie
import iammert.com.androidarchitecture.core.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FakeMovieRepository : MovieRepository {

    private val movies = MutableStateFlow<Map<MovieCategory, List<Movie>>>(emptyMap())

    fun setMovies(category: MovieCategory, list: List<Movie>) {
        movies.value = movies.value + (category to list)
    }

    override fun pagedMovies(category: MovieCategory): Flow<PagingData<Movie>> =
        flowOf(
            PagingData.from(
                data = movies.value[category].orEmpty(),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(endOfPaginationReached = true),
                    prepend = LoadState.NotLoading(endOfPaginationReached = true),
                    append = LoadState.NotLoading(endOfPaginationReached = true)
                )
            )
        )

    override fun observeMovie(id: Int): Flow<Movie?> =
        movies.map { all -> all.values.flatten().firstOrNull { it.id == id } }
}

fun testMovie(id: Int, title: String = "Movie $id") = Movie(
    id = id,
    title = title,
    originalTitle = title,
    overview = "Overview of $title",
    posterUrl = null,
    backdropUrl = null,
    originalLanguage = "en",
    voteAverage = 7.5,
    voteCount = 100,
    isAdult = false
)
