package com.sdimosikvip.weather.screens.manage_cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ManageCitiesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _weatherLocations = MutableLiveData<ResultResponse<List<WeatherLocationInfo>>>()
    val weatherLocations = _weatherLocations


    init {
        updateLocations()
    }

    fun updateLocations() {
        Timber.tag("mydebug").d("get saved location list")
        viewModelScope.launch {
            repository.getSavedLocationList().collect {
                _weatherLocations.value = it
            }
        }
    }

    fun saveAndUpdateWeatherLocations(weatherLocationInfo: WeatherLocationInfo) {
        Timber.tag("mydebug").d("save new location and get list")
        viewModelScope.launch {
            repository.saveAndUpdateLocationsList(weatherLocationInfo).collect {
                _weatherLocations.value = it
            }
        }
    }
}
