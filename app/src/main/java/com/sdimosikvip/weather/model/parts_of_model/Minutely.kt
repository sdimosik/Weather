package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class Minutely(
    @field:SerializedName("dt")
    val dt: Long,
    @field:SerializedName("precipitation")
    val precipitation: Double
)
