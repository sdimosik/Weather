package com.sdimosikvip.weather.data.model

import com.sdimosikvip.weather.base.BaseModel

data class HourWeather(
    override val id: Long = 0,
    val time: String,
    val path: String,
    val weather: String
) : BaseModel(id) {
    override fun isContentEqual(other: BaseModel): Boolean =
        other is HourWeather && id == other.id && time == other.time
                && path == other.path && weather == other.path
}
