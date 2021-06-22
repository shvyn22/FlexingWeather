package shvyn22.weatherapplication.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import shvyn22.weatherapplication.data.local.model.Location
import shvyn22.weatherapplication.data.local.model.Weather

interface ApiInterface {

    @GET("search/")
    suspend fun searchByName(
        @Query("query") query: String
    ): List<Location>

    @GET("search/")
    suspend fun searchByLattLong(
        @Query("lattlong") lattLong: String
    ): List<Location>

    @GET("{id}")
    suspend fun getWeather(
        @Path("id") id: Int
    ): Weather
}