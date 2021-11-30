package com.sdimosikvip.weather.mapper

import com.sdimosikvip.weather.model.domain.DayWeather
import com.sdimosikvip.weather.model.domain.HourWeather
import com.sdimosikvip.weather.model.domain.LocationWeather
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.parts_of_model.Daily
import com.sdimosikvip.weather.model.parts_of_model.Hourly

internal fun Hourly.toHourWeather(timezone: String): HourWeather {
    return HourWeather(
        id = this.dt,
        timestamp = this.dt,
        timezone = timezone,
        pathIcon = "https://openweathermap.org/img/wn/" + this.weatherInfo[0].icon + "@2x.png",
        temp = this.temp
    )
}

internal fun List<Hourly>.toHourWeatherList(timezone: String): List<HourWeather> {
    return this.map {
        it.toHourWeather(timezone)
    }
}

internal fun Daily.toDayWeather(timezone: String): DayWeather {
    return DayWeather(
        id = this.dt,
        timestamp = this.dt,
        timezone = timezone,
        pathIcon = "https://openweathermap.org/img/wn/" + this.weatherInfo[0].icon + "@2x.png",
        tempNight = this.temp.night,
        tempDay = this.temp.day,
        description = this.weatherInfo[0].description
    )
}

internal fun List<Daily>.toDayWeatherList(timezone: String): List<DayWeather> {
    return this.map {
        it.toDayWeather(timezone)
    }
}

internal fun WeatherLocationInfo.toLocationWeather(): LocationWeather {
    return LocationWeather(
        id = this.uId,
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        country = this.country,
        localNames = this.localNames,
        state = this.state
    )
}

internal fun List<WeatherLocationInfo>.toLocationWeatherList(): List<LocationWeather> {
    return this.map {
        it.toLocationWeather()
    }
}

internal fun LocationWeather.toWeatherLocationInfo(): WeatherLocationInfo {
    return WeatherLocationInfo(
        name = this.name,
        lat = this.lat,
        lon = this.lon,
        country = this.country,
        localNames = this.localNames,
        state = this.state
    )
}

internal fun List<LocationWeather>.toWeatherLocationInfoList(): List<WeatherLocationInfo> {
    return this.map {
        it.toWeatherLocationInfo()
    }
}
