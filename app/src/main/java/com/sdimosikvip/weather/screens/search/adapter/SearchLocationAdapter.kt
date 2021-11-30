package com.sdimosikvip.weather.screens.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sdimosikvip.weather.base.BaseAdapter
import com.sdimosikvip.weather.base.BaseViewHolder
import com.sdimosikvip.weather.databinding.ItemLocationWeatherBinding
import com.sdimosikvip.weather.databinding.ItemSearchLocationWeatherBinding
import com.sdimosikvip.weather.model.domain.LocationWeather

class SearchLocationAdapter(
    val onClick: (LocationWeather) -> Unit
) : BaseAdapter<LocationWeather, SearchLocationAdapter.Holder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchLocationAdapter.Holder =
        Holder(parent)

    inner class Holder(parent: ViewGroup) :
        BaseViewHolder<LocationWeather, ItemSearchLocationWeatherBinding>(
            ItemSearchLocationWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) {

        private lateinit var locationWeather: LocationWeather

        init {
            binding.container.setOnClickListener {
                onClick(locationWeather)
            }
        }

        override fun bind(item: LocationWeather) {
            locationWeather = item

            binding.cityName.text = item.name
            binding.stateName.text = item.state
            binding.countryName.text = item.country
        }
    }
}
