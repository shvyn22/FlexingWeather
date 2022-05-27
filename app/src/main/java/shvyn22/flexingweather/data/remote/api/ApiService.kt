package shvyn22.flexingweather.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather

interface ApiService {

    @GET("search/")
    suspend fun searchLocationsByName(
        @Query("query") query: String
    ): List<Location>

    @GET("{id}")
    suspend fun getWeather(
        @Path("id") id: Int
    ): Weather
}