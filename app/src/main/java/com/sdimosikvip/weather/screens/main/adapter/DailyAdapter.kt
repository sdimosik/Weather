package com.sdimosikvip.weather.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.sdimosikvip.weather.base.BaseAdapter
import com.sdimosikvip.weather.base.BaseViewHolder
import com.sdimosikvip.weather.databinding.ItemDailyWeatherBinding
import com.sdimosikvip.weather.model.domain.DayWeather
import com.sdimosikvip.weather.utils.TransformUtils

class DailyAdapter(
    val glide: RequestManager
) : BaseAdapter<DayWeather, DailyAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(parent)

    inner class Holder(parent: ViewGroup) : BaseViewHolder<DayWeather, ItemDailyWeatherBinding>(
        ItemDailyWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ) {

        private lateinit var dayWeather: DayWeather

        override fun bind(item: DayWeather) {
            dayWeather = item

            binding.itemDailyDayOfWeek.text =
                TransformUtils.formatDayOfWeek(item.timestamp, item.timezone)
            binding.itemDailyTempNightAndDay.text =
                TransformUtils.formatDayAndNightDef(item.tempNight, item.tempNight)

            binding.itemDailyDescription.text = item.description
            glide.load(item.pathIcon).into(binding.itemDailyImg)
        }
    }
}
