package shvyn22.flexingweather.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import shvyn22.flexingweather.R
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.presentation.ui.components.WeatherCard
import shvyn22.flexingweather.presentation.ui.components.WeatherItem
import shvyn22.flexingweather.util.Resource
import shvyn22.flexingweather.util.toStringDateTime
import shvyn22.flexingweather.util.toStringTime

@Composable
fun DetailsScreen(
    weather: Resource<Weather>,
    isFavorite: Boolean,
    onInsertItem: (Weather) -> Unit,
    onDeleteItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        when (weather) {
            is Resource.Success -> {
                var currItem by remember { mutableStateOf(weather.data.consolidated[0]) }

                val (
                    title,
                    iconFavorite,
                    lattLong,
                    time,
                    sunRiseSet,
                    iconSun,
                    lazyWeather,
                    cardWeather,
                ) = createRefs()

                Text(
                    text = weather.data.title,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(iconFavorite.start)
                        }
                        .padding(bottom = 5.dp)
                )

                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite
                    else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(iconFavorite) {
                            top.linkTo(title.top)
                            bottom.linkTo(title.bottom)
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            if (isFavorite) onDeleteItem(weather.data.woeId)
                            else onInsertItem(weather.data)
                        }
                )

                Text(
                    text = weather.data.lattLong,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .constrainAs(lattLong) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(bottom = 5.dp)
                )

                Text(
                    text = "${weather.data.time.toStringDateTime()} (${weather.data.timezone})",
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .constrainAs(time) {
                            top.linkTo(lattLong.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(bottom = 5.dp)
                )

                Icon(
                    imageVector = Icons.Outlined.WbSunny,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(iconSun) {
                            top.linkTo(time.bottom)
                            start.linkTo(parent.start)
                        }
                        .padding(bottom = 10.dp, end = 5.dp)
                )
                Text(
                    text = "${weather.data.sunRise.toStringTime()} -" +
                        " ${weather.data.sunSet.toStringTime()}",
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                    modifier = modifier
                        .constrainAs(sunRiseSet) {
                            top.linkTo(iconSun.top)
                            bottom.linkTo(iconSun.bottom)
                            start.linkTo(iconSun.end)
                        }
                        .padding(bottom = 10.dp)
                )

                WeatherCard(
                    weather = currItem,
                    modifier = Modifier
                        .constrainAs(cardWeather) {
                            top.linkTo(iconSun.bottom)
                            bottom.linkTo(lazyWeather.top)
                            start.linkTo(parent.start)
                        }
                )

                LazyRow(
                    modifier = Modifier
                        .constrainAs(lazyWeather) {
                            top.linkTo(cardWeather.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    items(weather.data.consolidated) { item ->
                        WeatherItem(
                            item = item,
                            onClick = {
                                currItem = item
                            }
                        )
                    }
                }
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(createRef()) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }

                )
            }
            is Resource.Error -> {
                Text(
                    text = stringResource(id = R.string.text_error, weather.msg),
                    modifier = Modifier
                        .constrainAs(createRef()) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
            is Resource.Idle -> Unit
        }
    }
}