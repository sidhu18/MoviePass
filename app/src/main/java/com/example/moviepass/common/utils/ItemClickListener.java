package com.example.moviepass.common.utils;

import android.view.View;

public interface ItemClickListener<T>{
    void onItemClick(View view, int position, T model);
}
