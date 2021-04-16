package shvyn22.weatherapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import shvyn22.weatherapplication.presentation.ui.theme.WeatherTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            mainViewModel.prefs.collect {
                setContent {
                    WeatherTheme(
                        isDarkMode = it.nightMode
                    ) {
                        MainScreen(
                            mainViewModel = mainViewModel,
                            it.nightMode
                        )
                    }
                }
            }
        }
    }
}