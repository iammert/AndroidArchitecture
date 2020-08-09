package iammert.com.androidarchitecture.ui.main.comedy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;
import iammert.com.androidarchitecture.databinding.FragmentMovieListBinding;
import iammert.com.androidarchitecture.enums.MovieType;
import iammert.com.androidarchitecture.ui.BaseFragment;
import iammert.com.androidarchitecture.ui.detail.comedy.MovieComedyDetailActivity;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MovieComedyListFragment extends BaseFragment<MovieComedyListViewModel, FragmentMovieListBinding> implements ComedyMovieListCallback {

    public static MovieComedyListFragment newInstance() {
        Bundle args = new Bundle();
        MovieComedyListFragment fragment = new MovieComedyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<MovieComedyListViewModel> getViewModel() {
        return MovieComedyListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_movie_list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        dataBinding.recyclerView.setAdapter(new MovieComedyListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getComedyMovies()
                .observe(getViewLifecycleOwner(), listResource -> dataBinding.setResource(listResource));
    }

    @Override
    public void onMovieClicked(ComedyMovieEntity movieEntity, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        startActivity(MovieComedyDetailActivity.newIntent(getActivity(), movieEntity.getId(), MovieType.COMEDY), options.toBundle());
    }
}
