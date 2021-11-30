package com.sdimosikvip.weather.screens.search

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
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading = _isLoading

    private val _searchLocationList = MutableLiveData<ResultResponse<List<WeatherLocationInfo>>>()
    val searchLocationList = _searchLocationList

    fun updateLocationsByName(city: String) {
        if (_isLoading.value == true) {
            return
        }
        _isLoading.value = true

        Timber.tag("mydebug").d("get search location list from string")
        viewModelScope.launch {
            repository.getWeatherLocationInfoByName(city).collect {
                _searchLocationList.value = ResultResponse.success(emptyList())
                _searchLocationList.value = it
                _isLoading.value = false
            }
        }

    }

    fun updateLocationsByCoordinates(lat: Double, lon: Double) {
        if (_isLoading.value == true) {
            return
        }
        _isLoading.value = true

        Timber.tag("mydebug").d("get search location list from string")
        viewModelScope.launch {
            repository.getWeatherLocationInfoByCoordinates(lat, lon).collect {
                _searchLocationList.value = ResultResponse.success(emptyList())
                _searchLocationList.value = it
                _isLoading.value = false
            }
        }
    }

}
