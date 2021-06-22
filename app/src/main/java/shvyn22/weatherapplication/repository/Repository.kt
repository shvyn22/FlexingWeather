package shvyn22.weatherapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.util.Resource

interface Repository {

    suspend fun searchByName(query: String): List<Location>

    suspend fun searchByLattLong(lattLong: String): List<Location>

    suspend fun getWeather(id: Int): Flow<Resource<Weather>>

    fun getItems(): Flow<List<Location>>

    fun isFavorite(id: Int): Flow<Boolean>

    suspend fun insert(item: Weather)

    suspend fun delete(id: Int)
}