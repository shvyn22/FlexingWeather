package shvyn22.flexingweather.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import shvyn22.flexingweather.data.local.model.Location
import shvyn22.flexingweather.data.local.model.Weather

interface ApiInterface {

    @GET("search/")
    suspend fun searchByName(
        @Query("query") query: String
    ): List<Location>

    @GET("{id}")
    suspend fun getWeather(
        @Path("id") id: Int
    ): Weather
}