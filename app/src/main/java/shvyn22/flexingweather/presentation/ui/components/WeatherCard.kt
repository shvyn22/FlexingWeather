package shvyn22.flexingweather.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.util.ICON_URL
import shvyn22.flexingweather.util.normaliseDate

@Composable
fun WeatherCard(
    weather: Weather.ConsolidatedWeather,
    modifier: Modifier
) {
    Card(
        elevation = 4.dp,
        modifier = modifier
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
                text = weather.date.normaliseDate(false),
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

            Image(
                painter = rememberImagePainter(
                    data = ICON_URL + "${weather.stateAbbr}.png",
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
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
                text = weather.stateName,
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
                text = "${weather.currTemp}℃",
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
                    weather.minTemp,
                    weather.maxTemp
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
                text = weather.windDirection,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .constrainAs(windDirection) {
                        top.linkTo(iconWind.top)
                        bottom.linkTo(iconWind.bottom)
                        start.linkTo(iconWind.end)
                    }
            )

            Text(
                text = "%.2f mph".format(weather.windSpeed),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .constrainAs(windSpeed) {
                        top.linkTo(iconWind.top)
                        bottom.linkTo(iconWind.bottom)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "Air pressure: ${weather.airPressure} mbar",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .constrainAs(airPressure) {
                        top.linkTo(iconWind.bottom)
                        start.linkTo(iconWind.start)
                    }
                    .padding(bottom = 5.dp)
            )

            Text(
                text = "Humidity: ${weather.humidity}%",
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
}