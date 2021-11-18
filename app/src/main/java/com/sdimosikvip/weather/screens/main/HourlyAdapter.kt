package com.sdimosikvip.weather.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.sdimosikvip.weather.base.BaseAdapter
import com.sdimosikvip.weather.base.BaseViewHolder
import com.sdimosikvip.weather.data.model.HourWeather
import com.sdimosikvip.weather.databinding.ItemHourlyWeatherBinding

class HourlyAdapter(val glide: RequestManager) :
    BaseAdapter<HourWeather, HourlyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder<HourWeather, ItemHourlyWeatherBinding>(
        ItemHourlyWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) {

        private lateinit var hourWeather: HourWeather

        override fun bind(item: HourWeather) {
            hourWeather = item

            binding.itemHourlyTime.text = item.time
            binding.itemHourlyTemp.text = item.weather

            glide.load(item.path).into(binding.itemHourlyImg)
        }
    }
}
