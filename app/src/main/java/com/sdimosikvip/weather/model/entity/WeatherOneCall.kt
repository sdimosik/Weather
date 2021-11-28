package com.sdimosikvip.weather.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sdimosikvip.weather.model.parts_of_model.*

@Entity(tableName = WeatherOneCall.TABLE_NAME)
data class WeatherOneCall(

    @SerializedName("alerts")
    val alerts: List<Alert>,
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("minutely")
    val minutely: List<Minutely>,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
) {
    @field:SerializedName("unique_id")
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0;

    companion object {
        const val TABLE_NAME = "weather_table"
    }
}
