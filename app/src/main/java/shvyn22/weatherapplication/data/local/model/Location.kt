package shvyn22.weatherapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Location(
    @PrimaryKey
    @ColumnInfo(name = "woeId")
    @SerializedName("woeid")
    val woeId: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "lattLong")
    @SerializedName("latt_long")
    val lattLong: String
)

