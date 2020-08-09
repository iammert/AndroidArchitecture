package iammert.com.androidarchitecture.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;
import iammert.com.androidarchitecture.data.local.entity.PopularMovieEntity;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadMovies();

    @Query("SELECT * FROM comedy_movies")
    LiveData<List<ComedyMovieEntity>> loadComedyMovies();

    @Query("SELECT * FROM popular_movies")
    LiveData<List<PopularMovieEntity>> loadPopularMovies();

    @Query("SELECT * FROM science_movies")
    LiveData<List<ScienceMovieEntity>> loadScienceMovie();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComedyMovies(List<ComedyMovieEntity> comedyMovieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePopularMovies(List<PopularMovieEntity> popularMovieEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveScienceMovies(List<ScienceMovieEntity> scienceMovieEntities);

    @Query("SELECT * FROM movies WHERE id=:id")
    LiveData<MovieEntity> getMovie(int id);

    @Query("SELECT * FROM comedy_movies WHERE _id=:id")
    LiveData<ComedyMovieEntity> getComedyMovie(int id);

    @Query("SELECT * FROM popular_movies WHERE _id=:id")
    LiveData<PopularMovieEntity> getPopularMovie(int id);

    @Query("SELECT * FROM science_movies WHERE _id=:id")
    LiveData<ScienceMovieEntity> getScienceMovie(int id);

}
