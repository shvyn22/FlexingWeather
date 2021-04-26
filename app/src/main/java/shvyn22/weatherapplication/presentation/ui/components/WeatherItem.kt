package shvyn22.weatherapplication.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.util.Constants.ICON_URL
import shvyn22.weatherapplication.util.normaliseDate

@Composable
fun WeatherItem(
    item: Weather.ConsolidatedWeather,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .clickable { onClick() }
        ) {
            Text(
                text = item.date.normaliseDate(true),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 5.dp)
            ) {
                GlideImage(
                    data = ICON_URL + "${item.stateAbbr}.png",
                    contentDescription = null,
                    fadeIn = true,
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
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}