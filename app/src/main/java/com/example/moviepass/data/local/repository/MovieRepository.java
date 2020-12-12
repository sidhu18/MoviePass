package com.example.moviepass.data.local.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moviepass.common.utils.AppExecutors;
import com.example.moviepass.data.local.AppDB;
import com.example.moviepass.data.local.dao.MoviesDao;
import com.example.moviepass.data.local.entities.MoviesEntity;
import com.example.moviepass.data.remote.api.RetrofitService;
import com.example.moviepass.data.remote.models.MoviesModel;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {

    private final MoviesDao moviesDao;
    private final Retrofit api;

    public MovieRepository(Application application) {
        AppDB database = AppDB.getInstance(application.getApplicationContext());
        moviesDao = database.moviesDao();
        api = RetrofitService.getRetrofitInstance();
    }

    public void insertMovie(MoviesEntity moviesEntity) {
        moviesDao.insertMovie(moviesEntity);
    }

    public void updateMovie(MoviesEntity moviesEntity) {
        moviesDao.updateMovie(moviesEntity);
    }

    public void deleteMovie(MoviesEntity moviesEntity) {
        moviesDao.deleteMovie(moviesEntity);
    }

    public LiveData<List<MoviesEntity>> getMovies() {
        refreshData();
        return moviesDao.getAllMovies();
    }

    private void refreshData() {
        RetrofitService.moviesApi.getMovies().enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                List<MoviesEntity> moviesEntities = response.body().getResults().stream().map(item ->
                        new MoviesEntity(
                                item.getId(),
                                item.getOriginalLanguage(),
                                item.getTitle(),
                                item.getOverview(),
                                item.getPopularity(),
                                item.getPosterPath(),
                                item.getBackdropPath(),
                                item.getVoteAverage(),
                                item.getVoteCount(),
                                item.getReleaseDate()
                        )).collect(Collectors.toList());

                AppExecutors.getInstance().diskIO().execute( () -> {
                    moviesDao.insertMovies(moviesEntities);
                });
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    public LiveData<MoviesEntity> getMovie(int id) {
        return moviesDao.getMovie(id);
    }
}
