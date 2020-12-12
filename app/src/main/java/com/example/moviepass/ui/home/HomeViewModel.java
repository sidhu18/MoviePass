package com.example.moviepass.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviepass.data.local.entities.MoviesEntity;
import com.example.moviepass.data.local.repository.MovieRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final MovieRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        movieList = repository.getMovies();
    }


    public LiveData<List<MoviesEntity>> movieList;

//    public LiveData<List<MoviesEntity>> getMovieList(){
//        if(movieList == null){
//            movieList = new MutableLiveData<>();
//        }
//        return movieList;
//    }

}
