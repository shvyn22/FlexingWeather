package shvyn22.weatherapplication.util

import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather

fun fromWeatherToLocation(item: Weather) =
    Location(
        woeId = item.woeId,
        title = item.title,
        lattLong = item.lattLong
    )