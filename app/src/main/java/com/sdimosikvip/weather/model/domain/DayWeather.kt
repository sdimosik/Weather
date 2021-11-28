package com.sdimosikvip.weather.model.domain

import com.sdimosikvip.weather.base.BaseModel

data class DayWeather(
    override val id: Long = 0,
    val timestamp: Long,
    val timezone: String,
    val pathIcon: String,
    val description: String,
    val tempNight: Double,
    val tempDay: Double
) : BaseModel(id) {
    override fun isContentEqual(other: BaseModel): Boolean =
        other is DayWeather && id == other.id && timestamp == other.timestamp
                && pathIcon == other.pathIcon && tempNight == other.tempNight
                && tempDay == other.tempDay && description == other.description
                && timezone == other.timezone
}
