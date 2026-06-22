package iammert.com.androidarchitecture.core.designsystem.animation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
val LocalAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

fun posterSharedKey(movieId: Int) = "poster-$movieId"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.sharedPoster(movieId: Int): Modifier {
    val sharedTransitionScope = LocalSharedTransitionScope.current ?: return this
    val animatedVisibilityScope = LocalAnimatedVisibilityScope.current ?: return this
    return with(sharedTransitionScope) {
        this@sharedPoster.sharedElement(
            sharedContentState = rememberSharedContentState(posterSharedKey(movieId)),
            animatedVisibilityScope = animatedVisibilityScope
        )
    }
}
