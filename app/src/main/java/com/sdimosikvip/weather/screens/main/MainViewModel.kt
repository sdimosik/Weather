package com.sdimosikvip.weather.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.model.entity.WeatherOneCall
import com.sdimosikvip.weather.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _weather = MutableLiveData<ResultResponse<WeatherOneCall>>()
    val weather = _weather

    private val _location = MutableLiveData<ResultResponse<WeatherLocationInfo>>()
    val location = _location

    private val _stateScroll = MutableLiveData<Int>(0)
    val stateScroll = _stateScroll

    init {
        init()
    }

    fun fetchWeather(locationWeather: WeatherLocationInfo) {
        Timber.tag("mydebug").d("Fetch weather")
        viewModelScope.launch {
            repository.getWeather(locationWeather).collect {
                _weather.value = it
            }
        }
    }

    fun fetchLocation() {
        Timber.tag("mydebug").d("get saved location")
        viewModelScope.launch {
            repository.getSavedLocation().collect {
                _location.value = it
            }
        }
    }

    fun init() {
        Timber.tag("mydebug").d("init data")
        fetchLocation()
    }

    fun fetchStateScroll(state: Int) {
        Timber.tag("mydebug").d("postValue for scroll")
        _stateScroll.postValue(state)
    }

    fun fetchNewLocation(locationWeather: ResultResponse<WeatherLocationInfo>) {
        Timber.tag("mydebug").d("postValue for new location")
        _location.postValue(locationWeather)
    }
}
