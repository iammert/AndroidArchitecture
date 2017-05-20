package iammert.com.androidarchitecture.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import iammert.com.androidarchitecture.AAApp;
import iammert.com.androidarchitecture.data.MovieRepository;
import iammert.com.androidarchitecture.data.local.MovieDatabase;
import iammert.com.androidarchitecture.data.local.dao.MovieDao;
import iammert.com.androidarchitecture.data.remote.ApiConstants;
import iammert.com.androidarchitecture.data.remote.MovieDBService;
import iammert.com.androidarchitecture.data.remote.RequestInterceptor;
import iammert.com.androidarchitecture.ui.main.MovieListViewModel;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mertsimsek on 20/05/2017.
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    MovieDBService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(MovieDBService.class);
    }

    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase() {
        return Room.databaseBuilder(application, MovieDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(MovieDatabase movieDatabase) {
        return movieDatabase.movieDao();
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieDao movieDao, MovieDBService movieDBService){
        return new MovieRepository(movieDao, movieDBService);
    }

}
