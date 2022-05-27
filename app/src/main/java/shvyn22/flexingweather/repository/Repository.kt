package shvyn22.flexingweather.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.util.Resource

interface Repository {

    suspend fun searchLocationsByName(query: String): Flow<Resource<List<Location>>>

    suspend fun getWeather(id: Int): Flow<Resource<Weather>>

    fun getFavoriteLocations(): Flow<Resource<List<Location>>>

    fun isLocationFavorite(id: Int): Flow<Boolean>

    suspend fun insertFavoriteLocation(item: Weather)

    suspend fun deleteFavoriteLocation(id: Int)
}