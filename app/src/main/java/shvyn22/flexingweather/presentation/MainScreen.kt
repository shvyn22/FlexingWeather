package shvyn22.flexingweather.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import shvyn22.flexingweather.R
import shvyn22.flexingweather.presentation.ui.components.NavigationConfig

@ExperimentalComposeUiApi
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    darkMode: Boolean
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onToggleMode(!darkMode)
                        }
                    ) {
                        Icon(
                            imageVector = if (darkMode) Icons.Filled.LightMode
                            else Icons.Filled.DarkMode,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) {
        NavigationConfig(
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(16.dp)
        )
    }
}