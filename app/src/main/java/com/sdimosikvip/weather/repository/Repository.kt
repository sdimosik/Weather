package com.sdimosikvip.weather.repository

import com.sdimosikvip.weather.model.entity.WeatherOneCall
import com.sdimosikvip.weather.sources.RemoteWeatherSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.model.entity.WeatherCoordinatesItem
import com.sdimosikvip.weather.db.WeatherDAO
import kotlinx.coroutines.flow.*
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteWeatherSource: RemoteWeatherSource,
    private val localWeatherSource: WeatherDAO
) {

    suspend fun getCoordinates(location: String): Flow<ResultResponse<List<WeatherCoordinatesItem>>> {
        return flow {
            emit(ResultResponse.loading())
            emit(remoteWeatherSource.fetchCoordinates(location))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWeather(lat: Double, lon: Double): Flow<ResultResponse<WeatherOneCall>> {
        return flow {

            emit(ResultResponse.loading<WeatherOneCall>())

            val weatherOld = localWeatherSource.getWeather()

            val responseStatus = remoteWeatherSource.fetchWeather(lat, lon)
            if (responseStatus.status == ResultResponse.Status.SUCCESS) {
                localWeatherSource.insertWeather(responseStatus.data!!)
                val weatherNew = localWeatherSource.getWeather()
                if (weatherNew != null) {
                    emit(ResultResponse.success(weatherNew))
                }
            } else {
                if (weatherOld != null) {
                    emit(ResultResponse.success(weatherOld))
                } else {
                    emit(responseStatus)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
