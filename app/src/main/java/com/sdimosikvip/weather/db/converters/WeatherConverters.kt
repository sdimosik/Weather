package com.sdimosikvip.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sdimosikvip.weather.model.parts_of_model.*
import java.lang.reflect.Type
import javax.inject.Inject

class WeatherConverters @Inject constructor() {
    val gson = Gson()
    val alertType: Type = object : TypeToken<List<Alert?>?>() {}.type
    val currentType: Type = object : TypeToken<Current?>() {}.type
    val dailyType: Type = object : TypeToken<List<Daily?>?>() {}.type
    val hourlyType: Type = object : TypeToken<List<Hourly?>?>() {}.type
    val minutelyType: Type = object : TypeToken<List<Minutely?>?>() {}.type
    val localNamesType: Type = object : TypeToken<LocalNames?>() {}.type

    @TypeConverter
    fun fromAlertList(item: List<Alert?>?): String {
        return gson.toJson(item, alertType)
    }

    @TypeConverter
    fun toAlertList(item: String?): List<Alert> {
        return gson.fromJson(item, alertType)
    }

    @TypeConverter
    fun fromCurrent(item: Current?): String {
        return gson.toJson(item, currentType)
    }

    @TypeConverter
    fun toCurrent(item: String?): Current {
        return gson.fromJson(item, currentType)
    }

    @TypeConverter
    fun fromDailyList(item: List<Daily?>?): String {
        return gson.toJson(item, dailyType)
    }

    @TypeConverter
    fun toDailyList(item: String?): List<Daily> {
        return gson.fromJson(item, dailyType)
    }

    @TypeConverter
    fun fromHourlyList(item: List<Hourly?>?): String {
        return gson.toJson(item, hourlyType)
    }

    @TypeConverter
    fun toHourlyList(item: String?): List<Hourly> {
        return gson.fromJson(item, hourlyType)
    }

    @TypeConverter
    fun fromMinutelyList(item: List<Minutely?>?): String {
        return gson.toJson(item, minutelyType)
    }

    @TypeConverter
    fun toMinutelyList(item: String?): List<Minutely> {
        return gson.fromJson(item, minutelyType)
    }

    @TypeConverter
    fun fromLocalNames(item: LocalNames): String {
        return gson.toJson(item, localNamesType)
    }

    @TypeConverter
    fun toLocalNames(item: String?): LocalNames {
        return gson.fromJson(item, localNamesType)
    }
}
