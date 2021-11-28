package com.sdimosikvip.weather.mapper

import com.sdimosikvip.weather.model.domain.DayWeather
import com.sdimosikvip.weather.model.domain.HourWeather
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
