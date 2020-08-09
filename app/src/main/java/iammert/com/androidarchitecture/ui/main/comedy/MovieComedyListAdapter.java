package iammert.com.androidarchitecture.ui.main.comedy;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;
import iammert.com.androidarchitecture.databinding.ItemComedyMovieListBinding;
import iammert.com.androidarchitecture.ui.BaseAdapter;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public class MovieComedyListAdapter extends BaseAdapter<MovieComedyListAdapter.MovieViewHolder, ComedyMovieEntity> {

    private final ComedyMovieListCallback movieListCallback;
    private List<ComedyMovieEntity> movieEntities;

    public MovieComedyListAdapter(@NonNull ComedyMovieListCallback movieListCallback) {
        movieEntities = new ArrayList<>();
        this.movieListCallback = movieListCallback;
    }

    @Override
    public void setData(List<ComedyMovieEntity> movieEntities) {
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

        ItemComedyMovieListBinding binding;

        public MovieViewHolder(ItemComedyMovieListBinding binding, ComedyMovieListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onMovieClicked(binding.getMovie(), binding.imageViewCover));
        }

        public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent, ComedyMovieListCallback callback) {
            ItemComedyMovieListBinding itemMovieListBinding = ItemComedyMovieListBinding.inflate(inflater, parent, false);
            return new MovieViewHolder(itemMovieListBinding, callback);
        }

        public void onBind(ComedyMovieEntity movieEntity) {
            binding.setMovie(movieEntity);
            binding.executePendingBindings();
        }
    }
}
