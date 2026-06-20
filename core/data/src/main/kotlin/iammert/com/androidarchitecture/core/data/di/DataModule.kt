package iammert.com.androidarchitecture.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iammert.com.androidarchitecture.core.data.repository.MovieRepository
import iammert.com.androidarchitecture.core.data.repository.OfflineFirstMovieRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(repository: OfflineFirstMovieRepository): MovieRepository
}
