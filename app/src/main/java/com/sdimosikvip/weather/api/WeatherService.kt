package com.sdimosikvip.weather.api

import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.entity.WeatherOneCall
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/onecall")
    suspend fun getWeatherOneCall(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Response<WeatherOneCall>

    @GET("geo/1.0/direct")
    suspend fun getCityInfoByCityName(
        @Query("q") location: String,
        @Query("limit") limit: Int = 1
    ): Response<List<WeatherLocationInfo>>

    @GET("geo/1.0/reverse")
    suspend fun getCityInfoByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 1
    ): Response<List<WeatherLocationInfo>>
}
