package shvyn22.flexingweather.util

import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather

fun fromWeatherToLocation(item: Weather) =
    Location(
        woeId = item.woeId,
        title = item.title,
        lattLong = item.lattLong
    )