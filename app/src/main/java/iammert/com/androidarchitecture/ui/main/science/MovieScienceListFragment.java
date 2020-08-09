package iammert.com.androidarchitecture.ui.main.science;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;
import iammert.com.androidarchitecture.databinding.FragmentMovieListBinding;
import iammert.com.androidarchitecture.enums.MovieType;
import iammert.com.androidarchitecture.ui.BaseFragment;
import iammert.com.androidarchitecture.ui.detail.science.MovieScienceDetailActivity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class MovieScienceListFragment extends BaseFragment<MovieScienceListViewModel, FragmentMovieListBinding> implements ScienceMovieListCallback {

    public static MovieScienceListFragment newInstance() {
        Bundle args = new Bundle();
        MovieScienceListFragment fragment = new MovieScienceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<MovieScienceListViewModel> getViewModel() {
        return MovieScienceListViewModel.class;
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
        dataBinding.recyclerView.setAdapter(new MovieScienceListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getScienceMovies()
                .observe(getViewLifecycleOwner(), listResource -> dataBinding.setResource(listResource));
    }

    @Override
    public void onMovieClicked(ScienceMovieEntity movieEntity, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        startActivity(MovieScienceDetailActivity.newIntent(getActivity(), movieEntity.getId(), MovieType.SCIENCE), options.toBundle());
    }
}
