package com.example.moviepass.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviepass.common.Constants;
import com.example.moviepass.data.local.dao.MoviesDao;
import com.example.moviepass.data.local.entities.MoviesEntity;

@Database(
        entities = {
                MoviesEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract MoviesDao moviesDao();

    public synchronized static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context,
                    AppDB.class,
                    Constants.APP_DB_NAME
            ).build();
        }
        return instance;
    }
}
