package shvyn22.weatherapplication.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.weatherapplication.api.ApiInterface
import shvyn22.weatherapplication.data.local.dao.LocationDao
import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather
import shvyn22.weatherapplication.util.Resource
import shvyn22.weatherapplication.util.fromWeatherToLocation
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiInterface,
    private val locationDao: LocationDao
) : Repository {

    override suspend fun searchByName(query: String): List<Location> =
        api.searchByName(query)

    override suspend fun searchByLattLong(lattLong: String): List<Location> =
        api.searchByLattLong(lattLong)

    override suspend fun getWeather(id: Int) = flow {
        emit(Resource.Loading())
        emit(Resource.Success(api.getWeather(id)))
    }


    override fun getItems(): Flow<List<Location>> =
        locationDao.getItems()

    override fun isFavorite(id: Int): Flow<Boolean> =
        locationDao.isFavorite(id)

    override suspend fun insert(item: Weather) {
        locationDao.insert(fromWeatherToLocation(item))
    }

    override suspend fun delete(id: Int) =
        locationDao.delete(id)
}