package iammert.com.androidarchitecture.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iammert.com.androidarchitecture.core.network.ApiConstants
import iammert.com.androidarchitecture.core.network.MovieDBService
import iammert.com.androidarchitecture.core.network.NetworkJson
import iammert.com.androidarchitecture.core.network.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        .readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        .addInterceptor(RequestInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideMovieDBService(okHttpClient: OkHttpClient): MovieDBService = Retrofit.Builder()
        .baseUrl(ApiConstants.ENDPOINT)
        .addConverterFactory(NetworkJson.converterFactory())
        .client(okHttpClient)
        .build()
        .create(MovieDBService::class.java)
}
