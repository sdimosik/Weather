package com.sdimosikvip.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.entity.WeatherOneCall

@Dao
interface WeatherDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherOneCall: WeatherOneCall)

    @Query("SELECT * FROM ${WeatherOneCall.TABLE_NAME} ORDER BY uId DESC LIMIT 1")
    suspend fun getWeather(): WeatherOneCall

    @Query("SELECT * FROM ${WeatherOneCall.TABLE_NAME} ORDER BY uId")
    suspend fun getAllWeather(): List<WeatherOneCall>

    @Query("DELETE FROM ${WeatherOneCall.TABLE_NAME}")
    suspend fun deleteAllWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinatesItem(locationInfo: WeatherLocationInfo)

    @Query("SELECT * FROM ${WeatherLocationInfo.TABLE_NAME} ORDER BY uId DESC LIMIT 1")
    suspend fun getCoordinatesItem(): WeatherLocationInfo

    @Query("SELECT * FROM ${WeatherLocationInfo.TABLE_NAME} ORDER BY uId")
    suspend fun getAllCoordinatesItem(): List<WeatherLocationInfo>

    @Query("DELETE FROM ${WeatherLocationInfo.TABLE_NAME}")
    suspend fun deleteAllCoordinatesItem()
}
