package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @field:SerializedName("day")
    val day: Double,
    @field:SerializedName("eve")
    val eve: Double,
    @field:SerializedName("morn")
    val morn: Double,
    @field:SerializedName("night")
    val night: Double
)
