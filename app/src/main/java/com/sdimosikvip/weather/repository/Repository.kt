package com.sdimosikvip.weather.repository

import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.db.WeatherDAO
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.entity.WeatherOneCall
import com.sdimosikvip.weather.sources.RemoteWeatherSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteWeatherSource: RemoteWeatherSource,
    private val localWeatherSource: WeatherDAO
) {

    suspend fun getWeatherLocationInfoByName(location: String): Flow<ResultResponse<List<WeatherLocationInfo>>> {
        return flow {
            emit(ResultResponse.loading())
            emit(remoteWeatherSource.fetchCityInfoByName(location))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWeatherLocationInfoByCoordinates(
        lat: Double,
        lon: Double
    ): Flow<ResultResponse<List<WeatherLocationInfo>>> {
        return flow {
            emit(ResultResponse.loading())
            emit(remoteWeatherSource.fetchCityInfoByCoordinates(lat, lon))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getWeather(
        locationInfo: WeatherLocationInfo,
    ): Flow<ResultResponse<WeatherOneCall>> {
        return flow {

            emit(ResultResponse.loading<WeatherOneCall>())
            val weatherOld = localWeatherSource.getWeather()
            val networkResponse =
                remoteWeatherSource.fetchWeather(locationInfo.lat, locationInfo.lon)
            if (networkResponse.status == ResultResponse.Status.SUCCESS) {
                localWeatherSource.insertWeather(networkResponse.data!!)
                emit(networkResponse)

            } else if (networkResponse.status == ResultResponse.Status.ERROR) {

                if (weatherOld != null) {
                    emit(ResultResponse.success(weatherOld))
                } else {
                    emit(networkResponse)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSavedLocationList(): Flow<ResultResponse<List<WeatherLocationInfo>>> {
        return flow {
            emit(ResultResponse.loading())

            val locations = localWeatherSource.getAllCoordinatesItem()
            if (locations != null) {
                emit(ResultResponse.success(locations))
            } else {
                Timber.tag("mydebug").d("get saved location list -> error")
                emit(ResultResponse.error("local not found"))
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSavedLocation(): Flow<ResultResponse<WeatherLocationInfo>> {
        return flow {
            emit(ResultResponse.loading())

            val locations = localWeatherSource.getCoordinatesItem()
            if (locations != null) {
                emit(ResultResponse.success(locations))
            } else {
                Timber.tag("mydebug").d("get saved location -> error")
                emit(ResultResponse.error("local not found"))
            }

        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveAndUpdateLocationsList(weatherLocationInfo: WeatherLocationInfo): Flow<ResultResponse<List<WeatherLocationInfo>>> {
        return flow {
            emit(ResultResponse.loading())
            if (weatherLocationInfo != null) {
                localWeatherSource.deleteAllCoordinatesItem()
                localWeatherSource.insertCoordinatesItem(weatherLocationInfo)
            }
            val locations = localWeatherSource.getAllCoordinatesItem()
            if (locations != null) {
                emit(ResultResponse.success(locations))
            } else {
                Timber.tag("mydebug").d("update and get saved location list -> error")
                emit(ResultResponse.error("local not found"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveAndUpdateLocation(weatherLocationInfo: WeatherLocationInfo): Flow<ResultResponse<WeatherLocationInfo>> {
        return flow {
            emit(ResultResponse.loading())
            if (weatherLocationInfo != null) {
                localWeatherSource.deleteAllCoordinatesItem()
                localWeatherSource.insertCoordinatesItem(weatherLocationInfo)
            }
            val location = localWeatherSource.getCoordinatesItem()
            if (location != null) {
                emit(ResultResponse.success(location))
            } else {
                Timber.tag("mydebug").d("update and get saved location list -> error")
                emit(ResultResponse.error("local not found"))
            }
        }.flowOn(Dispatchers.IO)
    }

}
