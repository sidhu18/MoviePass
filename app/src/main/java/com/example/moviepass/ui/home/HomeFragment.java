package com.example.moviepass.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.example.moviepass.common.base.BaseFragment;
import com.example.moviepass.databinding.FragmentHomeBinding;
import com.example.moviepass.ui.home.adapters.MovieListAdapter;

public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel> {

    private MovieListAdapter movieListAdapter;

    private HomeViewModel homeViewModel;

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater,container,false);
    }

    @Override
    protected void setUi(View view, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setAdapter();
        observeLiveData();
    }

    private void setAdapter() {
        movieListAdapter = new MovieListAdapter();
        binding.recyclerNewVideos.setAdapter(movieListAdapter);
    }

    private void observeLiveData() {
        homeViewModel.movieList.observe(getViewLifecycleOwner(), response -> movieListAdapter.setMoviesList(response));
    }

    @Override
    protected void setViewModel() {
//        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieListAdapter = null;
    }
}
