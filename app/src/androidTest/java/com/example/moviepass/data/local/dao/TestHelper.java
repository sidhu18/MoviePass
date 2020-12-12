package com.example.moviepass.data.local.dao;

import com.example.moviepass.data.local.entities.MoviesEntity;

public class TestHelper {

    public static MoviesEntity getSampleEntity(){
        return new MoviesEntity(
                1,
                "english",
                "Hugo",
                "Great movie",
                56.8,
                "",
                "",
                9.6,
                890,
                "56 788");
    }
}
