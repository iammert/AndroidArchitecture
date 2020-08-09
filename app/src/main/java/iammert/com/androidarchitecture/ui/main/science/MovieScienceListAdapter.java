package iammert.com.androidarchitecture.ui.main.science;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;
import iammert.com.androidarchitecture.databinding.ItemScienceMovieListBinding;
import iammert.com.androidarchitecture.ui.BaseAdapter;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class MovieScienceListAdapter extends BaseAdapter<MovieScienceListAdapter.MovieViewHolder, ScienceMovieEntity> {

    private final ScienceMovieListCallback movieListCallback;
    private List<ScienceMovieEntity> movieEntities;

    public MovieScienceListAdapter(@NonNull ScienceMovieListCallback movieListCallback) {
        movieEntities = new ArrayList<>();
        this.movieListCallback = movieListCallback;
    }

    @Override
    public void setData(List<ScienceMovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return MovieViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, movieListCallback);
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

        ItemScienceMovieListBinding binding;

        public MovieViewHolder(ItemScienceMovieListBinding binding, ScienceMovieListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onMovieClicked(binding.getMovie(), binding.imageViewCover));
        }

        public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent, ScienceMovieListCallback callback) {
            ItemScienceMovieListBinding itemMovieListBinding = ItemScienceMovieListBinding.inflate(inflater, parent, false);
            return new MovieViewHolder(itemMovieListBinding, callback);
        }

        public void onBind(ScienceMovieEntity movieEntity) {
            binding.setMovie(movieEntity);
            binding.executePendingBindings();
        }
    }
}
