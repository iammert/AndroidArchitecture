package iammert.com.androidarchitecture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import iammert.com.androidarchitecture.ui.detail.MovieDetailActivity
import iammert.com.androidarchitecture.ui.theme.ArchMoviesTheme

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ArchMoviesTheme {
                    MovieListRoute(
                        onMovieClick = { movie ->
                            startActivity(MovieDetailActivity.newIntent(requireContext(), movie.id))
                        }
                    )
                }
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}
