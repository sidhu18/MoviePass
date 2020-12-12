package com.example.moviepass.ui.home.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviepass.R;
import com.example.moviepass.common.Constants;
import com.example.moviepass.common.base.BaseViewHolder;
import com.example.moviepass.data.local.entities.MoviesEntity;
import com.example.moviepass.databinding.RecyclerItemNewVideosBinding;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.example.moviepass.common.Constants.POSTER_URL_BASE;

public class MovieListAdapter extends RecyclerView.Adapter<BaseViewHolder<MoviesEntity>> {

    private final AsyncListDiffer<MoviesEntity> differ = new AsyncListDiffer<>(
            this,
            new MovieListDiffer()
    );

    @NonNull
    @Override
    public BaseViewHolder<MoviesEntity> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MovieListViewHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<MoviesEntity> holder, int position) {
        MoviesEntity moviesEntity = getMoviesList().get(position);
        holder.bind(moviesEntity);
    }

    @Override
    public int getItemCount() {
        return getMoviesList().size();
    }

    public List<MoviesEntity> getMoviesList() {
        return differ.getCurrentList();
    }

    public void setMoviesList(List<MoviesEntity> moviesList) {
        differ.submitList(moviesList);
    }

    private static class MovieListViewHolder extends BaseViewHolder<MoviesEntity> {
        private final RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(),new RoundedCorners(8))
                .error(R.color.black);

        private final ViewBinding binding;
        public MovieListViewHolder(@NonNull ViewBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        public void bind(MoviesEntity item) {
            RecyclerItemNewVideosBinding videosBinding = (RecyclerItemNewVideosBinding) binding;

            videosBinding.movieTitle.setText(item.getTitle());
            videosBinding.textView2.setText(item.getReleaseDate());


            String url = POSTER_URL_BASE+ item.getPosterPath();
            Glide.with(itemView.getContext())
                    .load(url)
                    .transition(withCrossFade())
                    .apply(options)
                    .into(videosBinding.moviePoster);
        }

        public static MovieListViewHolder from(ViewGroup parent) {
            return new MovieListViewHolder(
                    RecyclerItemNewVideosBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    private static class MovieListDiffer extends DiffUtil.ItemCallback<MoviesEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull MoviesEntity oldItem, @NonNull MoviesEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MoviesEntity oldItem, @NonNull MoviesEntity newItem) {
            return oldItem.equals(newItem);
        }
    }
}
