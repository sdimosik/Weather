package com.sdimosikvip.weather.utils

import android.annotation.SuppressLint
import com.sdimosikvip.weather.utils.Extension.roundTo
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.math.RoundingMode
import java.text.DecimalFormat

class TransformUtils {
    companion object {
        const val delimiterDef = " /"
        const val celsius = "°C"
        const val percent = "%"
        const val meters_to_seconds = " m/s"
        const val kilometer = " km"
        const val hectopascal = " hPha"

        @SuppressLint("SimpleDateFormat")
        fun formatHoursAndMinutes(timestamp: Long, zone: String): String {
            val sdf = DateTimeFormatter.ofPattern("HH:mm")
            val zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of(zone))
            return sdf.format(zdt)
        }

        @SuppressLint("SimpleDateFormat")
        fun formatDayOfWeek(timestamp: Long, zone: String): String {
            val sdf = DateTimeFormatter.ofPattern("EEEE")
            val zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of(zone))
            return sdf.format(zdt)
        }

        @SuppressLint("SimpleDateFormat")
        fun formatMonthDayWeek(timestamp: Long, zone: String): String {
            val sdf = DateTimeFormatter.ofPattern("MMM dd EE")
            val zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of(zone))
            return sdf.format(zdt)
        }

        fun convertKelvinToCelsius(kelvin: Double): Double {
            return (kelvin - 273.15).roundTo(1)
        }

        fun convertCelsiusToFahrenheit(celsius: Double): Double {
            return (celsius * 1.8 + 32.0).roundTo(1)
        }

        fun convertKmToM(km: Double) = km * 1000.0
        fun convertMToKm(km: Double) = km / 1000.0

        fun formatKelvinToCelsius(item: Double) = convertKelvinToCelsius(item)
            .toString() + celsius

        fun formatAirPressureDef(item: Double) = item.toString() + hectopascal

        fun formatVisibilityDef(item: Double) = convertMToKm(item).toString() + kilometer

        fun formatCloudsDef(item: Double) = item.toString() + percent

        fun formatWindDef(item: Double) = item.toString() + meters_to_seconds

        fun formatHumidityDef(item: Double) = item.toString() + percent

        fun formatFeelsLikeDef(item: Double) = formatKelvinToCelsius(item)

        fun formatDayAndNightDef(item1: Double, item2: Double) = convertKelvinToCelsius(item1)
            .toString() + delimiterDef + convertKelvinToCelsius(item2).toString() + celsius

        fun formatHourlyTempDef(item: Double) = formatKelvinToCelsius(item)
    }
}
