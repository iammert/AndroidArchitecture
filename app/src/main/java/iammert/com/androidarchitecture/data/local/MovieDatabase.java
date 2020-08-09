package iammert.com.androidarchitecture.data.local;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import iammert.com.androidarchitecture.data.local.dao.MovieDao;
import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;
import iammert.com.androidarchitecture.data.local.entity.MovieEntity;
import iammert.com.androidarchitecture.data.local.entity.PopularMovieEntity;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */
@Database(entities = {MovieEntity.class, ComedyMovieEntity.class, PopularMovieEntity.class, ScienceMovieEntity.class}, version = 3)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'comedy_movies' (_id INTEGER NOT NULL, overview TEXT, voteAverage REAL NOT NULL, voteCount INTEGER NOT NULL, backdropPath TEXT, posterPath TEXT, adult INTEGER NOT NULL, title TEXT, originalLanguage TEXT, originalTitle TEXT, PRIMARY KEY('_id'))");
            database.execSQL("CREATE TABLE 'science_movies' (_id INTEGER NOT NULL, overview TEXT, voteAverage REAL NOT NULL, voteCount INTEGER NOT NULL, backdropPath TEXT, posterPath TEXT, adult INTEGER NOT NULL, title TEXT, originalLanguage TEXT, originalTitle TEXT, PRIMARY KEY('_id'))");
            database.execSQL("CREATE TABLE 'popular_movies' (_id INTEGER NOT NULL, overview TEXT, voteAverage REAL NOT NULL, voteCount INTEGER NOT NULL, backdropPath TEXT, posterPath TEXT, adult INTEGER NOT NULL, title TEXT, originalLanguage TEXT, originalTitle TEXT, PRIMARY KEY('_id'))");
        }
    };
}
