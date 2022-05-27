package shvyn22.flexingweather.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.util.getImageUrl
import shvyn22.flexingweather.util.normaliseDate

@Composable
fun WeatherItem(
    item: Weather.ConsolidatedWeather,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(5.dp)
                .clickable { onClick() }
        ) {
            Text(
                text = item.date.normaliseDate(true),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 5.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = getImageUrl(item.stateAbbr),
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .padding(end = 5.dp)
                )

                Text(
                    text = "${item.currTemp}℃",
                    style = MaterialTheme.typography.body1
                )
            }

            Text(
                text = "%.2f℃ / %.2f℃".format(
                    item.minTemp,
                    item.maxTemp
                ),
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}