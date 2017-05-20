package iammert.com.androidarchitecture.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.MovieEntity;
import iammert.com.androidarchitecture.databinding.ItemMovieListBinding;
import iammert.com.androidarchitecture.ui.BaseAdapter;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public class MovieListAdapter extends BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity> {

    private List<MovieEntity> movieEntities;

    public MovieListAdapter() {
        movieEntities = new ArrayList<>();
    }

    @Override
    public void setData(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return MovieViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int i) {
        viewHolder.onBind(movieEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemMovieListBinding itemMovieListBinding = ItemMovieListBinding.inflate(inflater, parent, false);
            return new MovieViewHolder(itemMovieListBinding);
        }

        ItemMovieListBinding binding;

        public MovieViewHolder(ItemMovieListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(MovieEntity movieEntity) {
            binding.setMovie(movieEntity);
            binding.executePendingBindings();
        }
    }
}
