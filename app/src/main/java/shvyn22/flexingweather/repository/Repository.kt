package shvyn22.flexingweather.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.util.Resource

interface Repository {

    suspend fun searchByName(query: String): Flow<Resource<List<Location>>>

    suspend fun getWeather(id: Int): Flow<Resource<Weather>>

    fun getItems(): Flow<Resource<List<Location>>>

    fun isFavorite(id: Int): Flow<Boolean>

    suspend fun insert(item: Weather)

    suspend fun delete(id: Int)
}