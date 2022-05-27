package shvyn22.flexingweather.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import shvyn22.flexingweather.presentation.MainViewModel
import shvyn22.flexingweather.presentation.ui.components.AppBar
import shvyn22.flexingweather.presentation.ui.components.NavigationConfig

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val isDarkTheme = !MaterialTheme.colors.isLight

    Scaffold(
        topBar = {
            AppBar(
                isDarkTheme = isDarkTheme,
                onToggleTheme = viewModel::editThemePreferences
            )
        },
    ) {
        NavigationConfig(
            navController = navController,
            viewModel = viewModel,
            modifier = modifier
                .padding(16.dp)
        )
    }
}