package com.sdimosikvip.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.entity.WeatherOneCall
import com.sdimosikvip.weather.db.converters.WeatherConverters

@Database(
    entities = [WeatherOneCall::class, WeatherLocationInfo::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    WeatherConverters::class
)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDAO(): WeatherDAO
}
