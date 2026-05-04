package iammert.com.androidarchitecture.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.asLiveData
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.model.Movie
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovie(id: Int): LiveData<Movie?> = movieRepository.observeMovie(id).asLiveData()
}
