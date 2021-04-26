package shvyn22.weatherapplication.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.presentation.ui.components.WeatherItem
import shvyn22.weatherapplication.util.Constants.ICON_URL
import shvyn22.weatherapplication.util.normaliseDate
import shvyn22.weatherapplication.util.toStringDateTime
import shvyn22.weatherapplication.util.toStringTime

@Composable
fun DetailsScreen(
    weather: Weather,
    isFavorite: (Int) -> Flow<Boolean>,
    onInsertItem: (Weather) -> Unit,
    onDeleteItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val currItem = remember { mutableStateOf(weather.consolidated[0]) }
    val isFavoriteState = isFavorite(weather.woeId).collectAsState(initial = false)

    ConstraintLayout(
        modifier = modifier
    ) {
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
            text = weather.title,
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
            imageVector = if (isFavoriteState.value) Icons.Filled.Favorite
                else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(iconFavorite) {
                    top.linkTo(title.top)
                    bottom.linkTo(title.bottom)
                    end.linkTo(parent.end)
                }
                .clickable {
                    if (isFavoriteState.value) onDeleteItem(weather.woeId)
                    else onInsertItem(weather)
                }
        )

        Text(
            text = weather.lattLong,
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
            text = "${weather.time.toStringDateTime()} (${weather.timezone})",
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
            text = "${weather.sunRise.toStringTime()} -> ${weather.sunSet.toStringTime()}",
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Start,
            modifier = modifier
                .constrainAs(sunRiseSet) {
                    top.linkTo(iconSun.top)
                    bottom.linkTo(iconSun.bottom)
                    start.linkTo(iconSun.end)
                }
        )

        Card(
            elevation = 4.dp,
            modifier = Modifier
                .constrainAs(cardWeather) {
                    top.linkTo(iconSun.bottom)
                    bottom.linkTo(lazyWeather.top)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .padding(bottom = 5.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.padding(16.dp)
            ) {
                val (
                    date,
                    iconState,
                    state,
                    iconTemp,
                    currTemp,
                    minMaxTemp,
                    iconWind,
                    windDirection,
                    windSpeed,
                    airPressure,
                    humidity,
                ) = createRefs()

                Text(
                    text = currItem.value.date.normaliseDate(false),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(date) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(bottom = 5.dp)
                )

                GlideImage(
                    data = ICON_URL + "${currItem.value.stateAbbr}.png",
                    contentDescription = null,
                    fadeIn = true,
                    modifier = Modifier
                        .constrainAs(iconState) {
                            top.linkTo(date.bottom)
                            start.linkTo(parent.start)
                        }
                        .width(32.dp)
                        .height(32.dp)
                        .padding(end = 5.dp, bottom = 5.dp)
                )

                Text(
                    text = currItem.value.stateName,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(state) {
                            top.linkTo(iconState.top)
                            bottom.linkTo(iconState.bottom)
                            start.linkTo(iconState.end)
                        }
                        .padding(bottom = 5.dp)
                )

                Icon(
                    imageVector = Icons.Filled.Thermostat,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(iconTemp) {
                            top.linkTo(iconState.bottom)
                            start.linkTo(iconState.start)
                        }
                        .height(32.dp)
                        .width(32.dp)
                        .padding(end = 5.dp, bottom = 5.dp)
                )

                Text(
                    text = "${currItem.value.currTemp}℃",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(currTemp) {
                            top.linkTo(iconTemp.top)
                            bottom.linkTo(iconTemp.bottom)
                            start.linkTo(iconTemp.end)
                        }
                )

                Text(
                    text = "%.2f℃ / %.2f℃".format(
                        currItem.value.minTemp,
                        currItem.value.maxTemp
                    ),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .constrainAs(minMaxTemp) {
                            top.linkTo(iconTemp.top)
                            bottom.linkTo(iconTemp.bottom)
                            end.linkTo(parent.end)
                        }
                )

                Icon(
                    imageVector = Icons.Filled.Air,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(iconWind) {
                            top.linkTo(iconTemp.bottom)
                            start.linkTo(iconTemp.start)
                        }
                        .height(32.dp)
                        .width(32.dp)
                        .padding(end = 5.dp, bottom = 5.dp)
                )

                Text(
                    text = currItem.value.windDirection,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(windDirection) {
                            top.linkTo(iconWind.top)
                            bottom.linkTo(iconWind.bottom)
                            start.linkTo(iconWind.end)
                        }
                )

                Text(
                    text = "%.2f mph".format(currItem.value.windSpeed),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(windSpeed) {
                            top.linkTo(iconWind.top)
                            bottom.linkTo(iconWind.bottom)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = "Air pressure: ${currItem.value.airPressure} mbar",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(airPressure) {
                            top.linkTo(iconWind.bottom)
                            start.linkTo(iconWind.start)
                        }
                        .padding(bottom = 5.dp)
                )

                Text(
                    text = "Humidity: ${currItem.value.humidity}%",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .constrainAs(humidity) {
                            top.linkTo(airPressure.bottom)
                            start.linkTo(airPressure.start)
                        }
                        .padding(bottom = 5.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .constrainAs(lazyWeather) {
                    top.linkTo(cardWeather.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(weather.consolidated) { item ->
                WeatherItem(
                    item = item,
                    onClick = {
                        currItem.value = item
                    })
            }
        }
    }
}