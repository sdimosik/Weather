package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class Daily(
    @field:SerializedName("clouds")
    val clouds: Double,
    @field:SerializedName("dew_point")
    val dewPoint: Double,
    @field:SerializedName("dt")
    val dt: Long,
    @field:SerializedName("feels_like")
    val feelsLike: FeelsLike,
    @field:SerializedName("humidity")
    val humidity: Double,
    @field:SerializedName("moon_phase")
    val moonPhase: Double,
    @field:SerializedName("moonrise")
    val moonrise: Long,
    @field:SerializedName("moonset")
    val moonset: Long,
    @field:SerializedName("pop")
    val pop: Double,
    @field:SerializedName("pressure")
    val pressure: Double,
    @field:SerializedName("rain")
    val rain: Double,
    @field:SerializedName("sunrise")
    val sunrise: Long,
    @field:SerializedName("sunset")
    val sunset: Long,
    @field:SerializedName("temp")
    val temp: Temp,
    @field:SerializedName("uvi")
    val uvi: Double,
    @field:SerializedName("weather")
    val weatherInfo: List<WeatherInfo>,
    @field:SerializedName("wind_deg")
    val windDeg: Double,
    @field:SerializedName("wind_gust")
    val windGust: Double,
    @field:SerializedName("wind_speed")
    val windSpeed: Double
)
