package com.sdimosikvip.weather.di

import android.content.Context
import androidx.room.Room
import com.sdimosikvip.weather.db.WeatherDAO
import com.sdimosikvip.weather.db.WeatherDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): WeatherDB {
        return Room.databaseBuilder(
            context,
            WeatherDB::class.java,
            "Weather.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideWeatherDAO(database: WeatherDB): WeatherDAO {
        return database.weatherDAO()
    }
}
