package com.sdimosikvip.weather.screens.main

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.appComponent
import com.sdimosikvip.weather.base.BaseFragment
import com.sdimosikvip.weather.databinding.MainFragmentBinding
import com.sdimosikvip.weather.extensions.setup
import com.sdimosikvip.weather.utils.autoCleared
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : BaseFragment(R.layout.main_fragment) {

    override var binding by autoCleared<MainFragmentBinding>()

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var animDrawable: AnimationDrawable

    private lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireContext().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)

        animDrawable = binding.root.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(2500)
        animDrawable.setExitFadeDuration(5000)

        hourlyAdapter = HourlyAdapter(Glide.with(this))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.hourlyWeatherList.collect {
                hourlyAdapter.bindData(it)
                binding.frgMainHourlyTempRv.hideShimmer()
            }
        }
    }

    private fun setupViews() {
        binding.frgMainHourlyTempRv.setup(hourlyAdapter, R.layout.item_hourly_weather)

        binding.frgMainCurrentTempNum.text = "4";
        binding.frgMainCurrentTempType.text = "°C"
        binding.frgMainCurrentTempDescription.text = "Cloudy"
    }

    override fun onResume() {
        super.onResume()
        animDrawable.start()
    }

    private fun makeBundle(): Bundle = bundleOf(MAIN_KEY to "")

    companion object {
        const val MAIN_KEY = "com.sdimosikvip.weather.screens.main.SERVICE_TITLE_KEY"
    }
}
