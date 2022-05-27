package shvyn22.flexingweather.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) DarkColors else LightColors,
        shapes = AppShapes,
        content = content
    )
}