package com.sdimosikvip.weather.sources

import com.sdimosikvip.weather.api.WeatherService
import javax.inject.Inject

class RemoteWeatherSource @Inject constructor(
    private val weatherService: WeatherService
) : BaseDataSource() {

    suspend fun fetchWeather(lat: Double, lon: Double) =
        getResult { weatherService.getWeatherOneCall(lat, lon) }

    suspend fun fetchCityInfoByName(locationName: String) =
        getResult { weatherService.getCityInfoByCityName(locationName) }

    suspend fun fetchCityInfoByCoordinates(lat: Double, lon: Double) =
        getResult { weatherService.getCityInfoByCoordinates(lat, lon) }
}
