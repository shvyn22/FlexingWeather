package shvyn22.flexingweather.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import shvyn22.flexingweather.api.ApiInterface
import shvyn22.flexingweather.data.local.dao.LocationDao
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.util.Resource
import shvyn22.flexingweather.util.fromWeatherToLocation
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiInterface,
    private val locationDao: LocationDao
) : Repository {

    override suspend fun searchByName(query: String) = flow {
        emit(Resource.Loading())
        try {
            val response = api.searchByName(query)
            if (response.isEmpty()) emit(Resource.Error(""))
            else emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }

    override suspend fun getWeather(id: Int) = flow {
        emit(Resource.Loading())
        try {
            val response = api.getWeather(id)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }

    override fun getItems() = flow {
        locationDao.getItems().collect {
            emit(Resource.Success(it))
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> =
        locationDao.isFavorite(id)

    override suspend fun insert(item: Weather) =
        locationDao.insert(fromWeatherToLocation(item))

    override suspend fun delete(id: Int) =
        locationDao.delete(id)
}