package com.sdimosikvip.weather.screens.manage_cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sdimosikvip.weather.base.BaseAdapter
import com.sdimosikvip.weather.base.BaseViewHolder
import com.sdimosikvip.weather.databinding.ItemLocationWeatherBinding
import com.sdimosikvip.weather.model.domain.LocationWeather

class LocationAdapter(
    val onClick: (LocationWeather) -> Unit) :
    BaseAdapter<LocationWeather, LocationAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.Holder =
        Holder(parent)

    inner class Holder(parent: ViewGroup) :
        BaseViewHolder<LocationWeather, ItemLocationWeatherBinding>(
            ItemLocationWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {

        private lateinit var locationWeather: LocationWeather

        init {
            binding.itemBuildingMcvCard.setOnClickListener {
                onClick(locationWeather)
            }
        }

        override fun bind(item: LocationWeather) {
            locationWeather = item

            binding.cityName.text = item.name
            binding.countryName.text = item.country
            binding.stateName.text = item.state
        }
    }
}
