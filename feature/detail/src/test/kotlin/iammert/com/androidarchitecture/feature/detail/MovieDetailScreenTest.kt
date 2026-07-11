package iammert.com.androidarchitecture.feature.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import iammert.com.androidarchitecture.core.designsystem.theme.ArchMoviesTheme
import iammert.com.androidarchitecture.core.testing.testMovie
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun showsTheMovieDetails() {
        composeRule.setContent {
            ArchMoviesTheme(dynamicColor = false) {
                MovieDetailScreen(uiState = MovieDetailUiState.Success(testMovie(1, title = "Arrival")), onBack = {})
            }
        }

        composeRule.onNodeWithText("Arrival").assertIsDisplayed()
        composeRule.onNodeWithText("Overview of Arrival").assertIsDisplayed()
        composeRule.onNodeWithText("7.5 (100 votes)").assertIsDisplayed()
        composeRule.onNodeWithText("EN").assertIsDisplayed()
    }

    @Test
    fun showsNotFound() {
        composeRule.setContent {
            ArchMoviesTheme(dynamicColor = false) {
                MovieDetailScreen(uiState = MovieDetailUiState.NotFound, onBack = {})
            }
        }

        composeRule.onNodeWithText("Movie not found").assertIsDisplayed()
    }

    @Test
    fun backButtonNavigatesBack() {
        var navigatedBack = false
        composeRule.setContent {
            ArchMoviesTheme(dynamicColor = false) {
                MovieDetailScreen(uiState = MovieDetailUiState.Loading, onBack = { navigatedBack = true })
            }
        }

        composeRule.onNodeWithContentDescription("Back").performClick()

        assertTrue(navigatedBack)
    }
}
