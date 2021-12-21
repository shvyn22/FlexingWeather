package shvyn22.flexingweather.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WeatherTheme(
    isDarkMode: Boolean?,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkMode == true) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        shapes = WeatherShapes,
        content = content
    )
}