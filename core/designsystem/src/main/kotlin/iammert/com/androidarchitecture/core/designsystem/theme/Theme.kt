package iammert.com.androidarchitecture.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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

private val DarkColors = darkColorScheme(
    primary = Accent,
    onPrimary = MainDark,
    primaryContainer = AccentDark,
    onPrimaryContainer = MainDarkVariant,
    secondary = Accent,
    onSecondary = MainDark,
    tertiary = Accent,
    background = MainDarkVariant,
    onBackground = Surface,
    surface = MainDark,
    onSurface = Surface,
    surfaceVariant = MainDarkVariant,
    onSurfaceVariant = SurfaceVariant
)

@Composable
fun ArchMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
