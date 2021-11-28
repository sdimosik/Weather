package com.sdimosikvip.weather.model.domain

import com.sdimosikvip.weather.base.BaseModel

data class HourWeather(
    override val id: Long = 0,
    val timestamp: Long,
    val timezone: String,
    val pathIcon: String,
    val temp: Double
) : BaseModel(id) {
    override fun isContentEqual(other: BaseModel): Boolean =
        other is HourWeather && id == other.id && timestamp == other.timestamp
                && pathIcon == other.pathIcon && temp == other.temp
                && timezone == other.timezone
}
