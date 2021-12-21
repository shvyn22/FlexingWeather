package shvyn22.flexingweather.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val white300 = Color(0xFFF0F0F0)
val white200 = Color(0xFFF5F5F5)

val black700 = Color(0xFF434343)
val black600 = Color(0xFF555555)

val teal700 = Color(0xFF069496)
val teal600 = Color(0xFF00A9AE)
val red900 = Color(0xFF960806)
val red800 = Color(0xFFA51612)

val LightColors = lightColors(
    primary = teal700,
    primaryVariant = teal600,
    onPrimary = Color.White,
    secondary = red900,
    secondaryVariant = red800,
    onSecondary = Color.White,
    surface = white200,
    onSurface = Color.Black,
    background = white300,
    onBackground = Color.Black
)

val DarkColors = darkColors(
    primary = teal700,
    primaryVariant = teal600,
    onPrimary = Color.White,
    secondary = red900,
    secondaryVariant = red800,
    onSecondary = Color.White,
    surface = black600,
    onSurface = Color.White,
    background = black700,
    onBackground = Color.White
)