package com.sdimosikvip.weather.screens.main

import androidx.lifecycle.ViewModel
import com.sdimosikvip.weather.data.model.HourWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _hourlyWeatherList: MutableStateFlow<List<HourWeather>> = MutableStateFlow(listOf())
    val hourlyWeatherList get() :StateFlow<List<HourWeather>> = _hourlyWeatherList

    init {
        _hourlyWeatherList.value =
            listOf(
                HourWeather(
                    1,
                    "08:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "4°C",
                ),
                HourWeather(
                    2,
                    "09:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "4°C",
                ),
                HourWeather(
                    3,
                    "10:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "5°C",
                ),
                HourWeather(
                    4,
                    "11:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "7°C",
                ),
                HourWeather(
                    5,
                    "12:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "15°C",
                ),
                HourWeather(
                    6,
                    "13:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "15°C",
                ),
                HourWeather(
                    7,
                    "14:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "14°C",
                ),
                HourWeather(
                    8,
                    "15:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "12°C",
                ),
                HourWeather(
                    9,
                    "16:00",
                    "https://openweathermap.org/img/wn/10d@2x.png",
                    "10°C",
                ),
            )
    }
}
