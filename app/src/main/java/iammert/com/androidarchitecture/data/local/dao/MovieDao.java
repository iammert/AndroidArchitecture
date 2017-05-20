package iammert.com.androidarchitecture.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.MovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntities);

    @Query("SELECT * FROM movies WHERE id=:id")
    LiveData<List<MovieEntity>> getMovie(int id);

}
