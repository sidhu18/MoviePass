package com.example.moviepass.common.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements ViewBinder<T>{

    public BaseViewHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
    }
}
