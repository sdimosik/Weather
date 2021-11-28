package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class Hourly(
    @field:SerializedName("clouds")
    val clouds: Double,
    @field:SerializedName("dew_point")
    val dewPoint: Double,
    @field:SerializedName("dt")
    val dt: Long,
    @field:SerializedName("feels_like")
    val feelsLike: Double,
    @field:SerializedName("humidity")
    val humidity: Double,
    @field:SerializedName("pop")
    val pop: Double,
    @field:SerializedName("pressure")
    val pressure: Double,
    @field:SerializedName("temp")
    val temp: Double,
    @field:SerializedName("uvi")
    val uvi: Double,
    @field:SerializedName("visibility")
    val visibility: Double,
    @field:SerializedName("weather")
    val weatherInfo: List<WeatherInfo>,
    @field:SerializedName("wind_deg")
    val windDeg: Double,
    @field:SerializedName("wind_gust")
    val windGust: Double,
    @field:SerializedName("wind_speed")
    val windSpeed: Double
)
