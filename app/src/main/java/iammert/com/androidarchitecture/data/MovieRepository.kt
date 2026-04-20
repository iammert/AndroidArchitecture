package iammert.com.androidarchitecture.data

import androidx.lifecycle.LiveData
import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.data.remote.MovieDBService
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse
import retrofit2.Call
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val movieDBService: MovieDBService
) {

    fun loadPopularMovies(): LiveData<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {
            override fun saveCallResult(item: MoviesResponse) = movieDao.saveMovies(item.results)

            override fun loadFromDb(): LiveData<List<MovieEntity>> = movieDao.loadMovies()

            override fun createCall(): Call<MoviesResponse> = movieDBService.loadMovies()
        }.asLiveData

    fun getMovie(id: Int): LiveData<MovieEntity> = movieDao.getMovie(id)
}
