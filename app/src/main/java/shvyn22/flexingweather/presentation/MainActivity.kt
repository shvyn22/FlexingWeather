package shvyn22.flexingweather.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingweather.presentation.ui.theme.WeatherTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val nightMode = viewModel.nightMode.collectAsState(initial = false)

            WeatherTheme(
                isDarkMode = nightMode.value
            ) {
                MainScreen(
                    viewModel = viewModel,
                    darkMode = nightMode.value
                )
            }
        }
    }
}