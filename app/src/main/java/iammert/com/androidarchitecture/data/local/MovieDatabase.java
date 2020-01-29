package iammert.com.androidarchitecture.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import iammert.com.androidarchitecture.data.local.dao.MovieDao;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */
@Database(entities = {MovieEntity.class}, version = 2)
public abstract class MovieDatabase extends RoomDatabase{

    public abstract MovieDao movieDao();
}
