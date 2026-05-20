package iammert.com.androidarchitecture.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.data.model.Movie
import iammert.com.androidarchitecture.data.model.MovieCategory
import iammert.com.androidarchitecture.ui.main.MovieListRoute
import kotlinx.coroutines.launch

private enum class HomeTab(@StringRes val title: Int, val category: MovieCategory) {
    POPULAR(R.string.tab_popular, MovieCategory.POPULAR),
    SCIENCE(R.string.tab_science, MovieCategory.SCIENCE_FICTION),
    COMEDY(R.string.tab_comedy, MovieCategory.COMEDY)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onMovieClick: (Movie) -> Unit) {
    val tabs = HomeTab.entries
    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(stringResource(tab.title)) }
                    )
                }
            }
            HorizontalPager(state = pagerState) { page ->
                MovieListRoute(category = tabs[page].category, onMovieClick = onMovieClick)
            }
        }
    }
}
