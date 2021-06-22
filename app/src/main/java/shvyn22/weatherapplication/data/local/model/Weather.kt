package shvyn22.weatherapplication.data.local.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    @SerializedName("woeid")
    val woeId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("latt_long")
    val lattLong: String,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("sun_rise")
    val sunRise: String,

    @SerializedName("sun_set")
    val sunSet: String,

    @SerializedName("consolidated_weather")
    val consolidated: List<ConsolidatedWeather>
) : Parcelable {
    @Parcelize
    data class ConsolidatedWeather(
        @SerializedName("weather_state_name")
        val stateName: String,

        @SerializedName("weather_state_abbr")
        val stateAbbr: String,

        @SerializedName("wind_direction_compass")
        val windDirection: String,

        @SerializedName("wind_speed")
        val windSpeed: Float,

        @SerializedName("applicable_date")
        val date: String,

        @SerializedName("min_temp")
        val minTemp: Float,

        @SerializedName("max_temp")
        val maxTemp: Float,

        @SerializedName("the_temp")
        val currTemp: Float,

        @SerializedName("air_pressure")
        val airPressure: Float,

        @SerializedName("humidity")
        val humidity: Float
    ) : Parcelable
}
