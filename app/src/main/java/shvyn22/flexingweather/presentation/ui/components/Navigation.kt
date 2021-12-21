package shvyn22.flexingweather.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import shvyn22.flexingweather.presentation.MainViewModel
import shvyn22.flexingweather.presentation.browse.BrowseScreen
import shvyn22.flexingweather.presentation.details.DetailsScreen

enum class Routes(val route: String) {
    Browse("browse"),
    Details("details/{id}")
}

@ExperimentalComposeUiApi
@Composable
fun NavigationConfig(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Browse.route
    ) {
        composable(route = Routes.Browse.route) {
            val locations = viewModel.locations.collectAsState()
            BrowseScreen(
                locations = locations.value,
                searchItems = viewModel::searchItems,
                onNavigateToDetails = {
                    viewModel.getWeather(it)
                    navController.navigate("details/$it")
                },
                modifier = modifier
            )
        }

        composable(
            route = Routes.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            entry.arguments?.getInt("id")?.let { id ->
                val weather = viewModel.weather.collectAsState()
                val isFavorite = viewModel.isFavorite(id).collectAsState(initial = false)
                DetailsScreen(
                    weather = weather.value,
                    isFavorite = isFavorite.value,
                    onInsertItem = viewModel::onInsertItem,
                    onDeleteItem = viewModel::onDeleteItem,
                    modifier = modifier
                )
            }
        }
    }
}