package iammert.com.androidarchitecture.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import iammert.com.androidarchitecture.ui.theme.ArchMoviesTheme

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchMoviesTheme {
                MovieDetailRoute(onBack = ::finish)
            }
        }
    }

    companion object {
        fun newIntent(context: Context, movieId: Int): Intent =
            Intent(context, MovieDetailActivity::class.java).putExtra(MovieDetailViewModel.KEY_MOVIE_ID, movieId)
    }
}
