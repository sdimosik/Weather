package com.sdimosikvip.weather.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.model.entity.WeatherOneCall
import com.sdimosikvip.weather.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _isUpdating = MutableLiveData<Boolean>(true)

    private val _weather = MutableLiveData<ResultResponse<WeatherOneCall>>()
    val weather = _weather

    private val _stateScroll = MutableLiveData<Int>(0)
    val stateScroll = _stateScroll

    private val lat: Double = 59.57
    private val lon: Double = 30.19

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        if (_isUpdating.value == true) {
            _isUpdating.value = false

            viewModelScope.launch {
                repository.getWeather(lat, lon).collect {
                    _weather.value = it
                    _isUpdating.value = true
                }
            }
        }
    }

    fun fetchStateScroll(state: Int) {
        _stateScroll.postValue(state)
    }
}
