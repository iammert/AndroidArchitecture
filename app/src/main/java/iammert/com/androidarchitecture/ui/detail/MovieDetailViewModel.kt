package iammert.com.androidarchitecture.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovie(id: Int): LiveData<MovieEntity> = movieRepository.getMovie(id)
}
