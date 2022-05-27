package shvyn22.flexingweather.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingweather.presentation.main.MainScreen
import shvyn22.flexingweather.presentation.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val theme = viewModel.isDarkTheme.collectAsState(initial = false)

            AppTheme(
                isDarkTheme = theme.value
            ) {
                MainScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}