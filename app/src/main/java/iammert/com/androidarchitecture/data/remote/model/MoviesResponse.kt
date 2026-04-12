package iammert.com.androidarchitecture.data.remote.model

import iammert.com.androidarchitecture.data.local.entity.MovieEntity

data class MoviesResponse(val results: List<MovieEntity> = emptyList())
