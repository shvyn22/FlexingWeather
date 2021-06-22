package shvyn22.weatherapplication.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import shvyn22.weatherapplication.presentation.MainViewModel
import shvyn22.weatherapplication.presentation.browse.BrowseScreen
import shvyn22.weatherapplication.presentation.details.DetailsScreen

enum class Routes(val route: String) {
    Browse("browse"),
    Details("details")
}

@ExperimentalComposeUiApi
@Composable
fun NavigationConfig(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Browse.route
    ) {
        composable(route = Routes.Browse.route) {
            mainViewModel.resetWeather()
            BrowseScreen(
                locations = mainViewModel.items,
                searchValue = mainViewModel.searchValue,
                searchItems = mainViewModel::searchItems,
                getWeather = mainViewModel::getWeather,
                currWeather = mainViewModel.currWeather,
                navController = navController,
                modifier = modifier
            )
        }

        composable(
            route = Routes.Details.route
        ) {
            mainViewModel.currWeather.value.data?.let { weather ->
                DetailsScreen(
                    weather = weather,
                    isFavorite = mainViewModel::isFavorite,
                    onInsertItem = mainViewModel::onInsertItem,
                    onDeleteItem = mainViewModel::onDeleteItem,
                    modifier = modifier
                )
            }
        }
    }
}