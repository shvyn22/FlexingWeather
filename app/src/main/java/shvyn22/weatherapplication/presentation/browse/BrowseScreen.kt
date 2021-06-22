package shvyn22.weatherapplication.presentation.browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import shvyn22.weatherapplication.R
import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.presentation.ui.components.LocationItem
import shvyn22.weatherapplication.presentation.ui.components.Routes
import shvyn22.weatherapplication.util.Resource

@ExperimentalComposeUiApi
@Composable
fun BrowseScreen(
    locations: MutableState<List<Location>>,
    searchValue: MutableState<String>,
    searchItems: (String) -> Unit,
    getWeather: (Int) -> Unit,
    currWeather: MutableState<Resource<Weather>>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (currWeather.value is Resource.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (currWeather.value is Resource.Success) {
            navController.navigate(Routes.Details.route)
        }
        Column {
            OutlinedTextField(
                value = searchValue.value,
                onValueChange = {
                    searchValue.value = it
                    if (it.isEmpty()) searchItems(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                label = {
                    Text(text = stringResource(id = R.string.text_search_placeholder))
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        searchItems(searchValue.value)
                        keyboard?.hide()
                    }
                ),
            )

            LazyColumn {
                items(locations.value) { item ->
                    LocationItem(
                        item = item,
                        onClick = {
                            getWeather(item.woeId)
                        }
                    )
                }
            }
        }
    }
}