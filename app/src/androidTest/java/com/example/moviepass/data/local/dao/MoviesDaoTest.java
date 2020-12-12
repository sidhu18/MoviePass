package com.example.moviepass.data.local.dao;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.moviepass.data.local.AppDB;
import com.example.moviepass.data.local.entities.MoviesEntity;
import com.example.moviepass.util.LiveDataTestUtil;

import static com.google.common.truth.Truth.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.example.moviepass.data.local.dao.TestHelper.getSampleEntity;

public class MoviesDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AppDB db;
    private MoviesDao dao;

    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context,AppDB.class).build();
        dao = db.moviesDao();
    }

    @After
    public void teardown() {
        db.close();
    }


    @Test
    public void insertMovie(){
        MoviesEntity moviesEntity = getSampleEntity();
        dao.insertMovie(moviesEntity);

        try {
            int id = 1;
            MoviesEntity movie = LiveDataTestUtil.getOrAwaitValue(dao.getMovie(id));
            assertThat(movie.getId() == id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateMovie(){
        MoviesEntity moviesEntity = getSampleEntity();
        dao.insertMovie(moviesEntity);
        String newTitle = "Mad max";

        MoviesEntity updatedMoviesEntity = new MoviesEntity(
                1,
                "english",
                newTitle,
                "Great movie",
                56.8,
                "",
                "",
                9.6,
                890,
                "78 67");
        dao.updateMovie(updatedMoviesEntity);
        try {
            int id = 1;
            MoviesEntity movie = LiveDataTestUtil.getOrAwaitValue(dao.getMovie(id));
            assertThat(movie.getTitle().equals(newTitle));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteMovie(){
        MoviesEntity moviesEntity = getSampleEntity();
        dao.insertMovie(moviesEntity);

        dao.deleteMovie(moviesEntity);
        try {
            int id = 1;
            MoviesEntity movie = LiveDataTestUtil.getOrAwaitValue(dao.getMovie(id));
            assertThat(movie).isNull();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
