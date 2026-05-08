package iammert.com.androidarchitecture.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = MainDark,
    onPrimary = Surface,
    primaryContainer = MainDarkVariant,
    onPrimaryContainer = Surface,
    secondary = Accent,
    onSecondary = MainDark,
    tertiary = AccentDark,
    surface = Surface,
    onSurface = MainDark,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = MainDark
)

@Composable
fun ArchMoviesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
