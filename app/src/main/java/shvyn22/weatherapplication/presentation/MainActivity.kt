package shvyn22.weatherapplication.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.weatherapplication.presentation.ui.theme.WeatherTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = mainViewModel.prefs.collectAsState(initial = false)
            val theme by remember { themeState }

            WeatherTheme(
                isDarkMode = theme
            ) {
                MainScreen(
                    mainViewModel = mainViewModel,
                    darkMode = theme
                )
            }
        }
    }
}