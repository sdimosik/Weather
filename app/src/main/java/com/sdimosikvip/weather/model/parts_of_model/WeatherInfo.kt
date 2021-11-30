package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("icon")
    val icon: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("main")
    val main: String
)
