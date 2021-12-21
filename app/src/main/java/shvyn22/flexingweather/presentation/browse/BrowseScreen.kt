package shvyn22.flexingweather.presentation.browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import shvyn22.flexingweather.R
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.presentation.ui.components.LocationItem
import shvyn22.flexingweather.util.Resource

@ExperimentalComposeUiApi
@Composable
fun BrowseScreen(
    locations: Resource<List<Location>>,
    searchItems: (String) -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboard = LocalSoftwareKeyboardController.current
    var searchValue by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchValue,
                onValueChange = {
                    searchValue = it
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
                        searchItems(searchValue)
                        keyboard?.hide()
                    }
                ),
            )

            when (locations) {
                is Resource.Success -> {
                    LazyColumn {
                        items(locations.data) { item ->
                            LocationItem(
                                item = item,
                                onClick = {
                                    onNavigateToDetails(item.woeId)
                                }
                            )
                        }
                    }
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Error -> {
                    Text(text = stringResource(id = R.string.text_not_found))
                }
                is Resource.Idle -> Unit
            }
        }
    }
}