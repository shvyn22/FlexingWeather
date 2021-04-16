package shvyn22.weatherapplication.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Location(
    @PrimaryKey
    @SerializedName("woeid")
    val woeId: Int,

    val title: String,

    @SerializedName("latt_long")
    val lattLong: String
)
