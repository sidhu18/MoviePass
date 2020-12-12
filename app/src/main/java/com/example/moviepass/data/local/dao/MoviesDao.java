package com.example.moviepass.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviepass.data.local.entities.MoviesEntity;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MoviesEntity movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MoviesEntity> posts);

    @Update
    void updateMovie(MoviesEntity movie);

    @Delete
    void deleteMovie(MoviesEntity movie);

    @Query("SELECT * FROM movies")
    LiveData<List<MoviesEntity>> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<MoviesEntity> getMovie(int id);
}
