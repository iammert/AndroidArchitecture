package iammert.com.androidarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.Resource
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieListViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    val popularMovies: LiveData<Resource<List<MovieEntity>>> = movieRepository.loadPopularMovies()
}
