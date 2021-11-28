package com.sdimosikvip.weather.sources

import com.sdimosikvip.weather.api.WeatherService
import javax.inject.Inject

class RemoteWeatherSource @Inject constructor(
    private val weatherService: WeatherService
) : BaseDataSource() {

    suspend fun fetchWeather(lat: Double, lon: Double) =
        getResult { weatherService.getWeatherOneCall(lat, lon) }

    suspend fun fetchCoordinates(location: String) =
        getResult { weatherService.getCoordinates(location) }
}
