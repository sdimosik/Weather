package com.sdimosikvip.weather.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.sdimosikvip.weather.base.BaseAdapter
import com.sdimosikvip.weather.base.BaseViewHolder
import com.sdimosikvip.weather.databinding.ItemHourlyWeatherBinding
import com.sdimosikvip.weather.model.domain.HourWeather
import com.sdimosikvip.weather.utils.TransformUtils

class HourlyAdapter(
    val glide: RequestManager
) : BaseAdapter<HourWeather, HourlyAdapter.Holder>() {

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

            binding.itemHourlyTime.text =
                TransformUtils.formatHoursAndMinutes(item.timestamp, item.timezone)
            binding.itemHourlyTemp.text = TransformUtils.formatHourlyTempDef(item.temp)

            glide.load(item.pathIcon).into(binding.itemHourlyImg)
        }
    }
}
