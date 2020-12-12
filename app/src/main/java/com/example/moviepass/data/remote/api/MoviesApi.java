package com.example.moviepass.data.remote.api;

import com.example.moviepass.data.remote.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.moviepass.common.Constants.API_KEY;
import static com.example.moviepass.common.Constants.LANGUAGE;

public interface MoviesApi {

    @GET("upcoming?api_key=" + API_KEY + "&language=" + LANGUAGE + "&page=1&region=IN|US&with_release_type=2|3")
    Call<MoviesModel> getMovies();
}
