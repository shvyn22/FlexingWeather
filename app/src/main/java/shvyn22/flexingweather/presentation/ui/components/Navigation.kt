package shvyn22.flexingweather.presentation.ui.components

import androidx.compose.runtime.Composable
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

enum class Screen(val route: String) {
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
        startDestination = Screen.Browse.route
    ) {
        composable(route = Screen.Browse.route) {
            BrowseScreen(
                viewModel = viewModel,
                onNavigateToDetails = {
                    navController.navigate("details/$it")
                },
                modifier = modifier
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            entry.arguments?.getInt("id")?.let { id ->
                DetailsScreen(
                    weatherId = id,
                    viewModel = viewModel,
                    modifier = modifier
                )
            }
        }
    }
}