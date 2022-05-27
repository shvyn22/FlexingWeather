package shvyn22.flexingweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import shvyn22.flexingweather.data.local.dao.LocationDao
import shvyn22.flexingweather.data.local.model.Location

@Database(
    entities = [Location::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
}