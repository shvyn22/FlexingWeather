package shvyn22.flexingweather.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.flexingweather.data.local.dao.LocationDao
import shvyn22.flexingweather.data.local.model.Weather
import shvyn22.flexingweather.data.remote.api.ApiService
import shvyn22.flexingweather.data.util.fromWeatherToLocation
import shvyn22.flexingweather.util.Resource

class RepositoryImpl(
    private val api: ApiService,
    private val locationDao: LocationDao
) : Repository {

    override suspend fun searchLocationsByName(query: String) = flow {
        emit(Resource.Loading())
        try {
            val response = api.searchLocationsByName(query)
            if (response.isEmpty())
                emit(Resource.Error(""))
            else
                emit(Resource.Success(response))
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

    override fun getFavoriteLocations() = flow {
        locationDao.getFavoriteLocations().collect {
            emit(Resource.Success(it))
        }
    }

    override fun isLocationFavorite(id: Int): Flow<Boolean> =
        locationDao.isLocationFavorite(id)

    override suspend fun insertFavoriteLocation(item: Weather) =
        locationDao.insertFavoriteLocation(fromWeatherToLocation(item))

    override suspend fun deleteFavoriteLocation(id: Int) =
        locationDao.deleteFavoriteLocation(id)
}