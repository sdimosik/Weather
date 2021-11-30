package com.sdimosikvip.weather.screens.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.base.ToolbarFragment
import com.sdimosikvip.weather.databinding.MainFragmentBinding
import com.sdimosikvip.weather.extensions.setup
import com.sdimosikvip.weather.mapper.toDayWeatherList
import com.sdimosikvip.weather.mapper.toHourWeatherList
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.screens.main.adapter.DailyAdapter
import com.sdimosikvip.weather.screens.main.adapter.HourlyAdapter
import com.sdimosikvip.weather.utils.TransformUtils
import com.sdimosikvip.weather.utils.TransformUtils.Companion.formatWindDef
import com.sdimosikvip.weather.utils.autoCleared
import com.sdimosikvip.weather.utils.getColorHelper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : ToolbarFragment(
    titleResource = R.string.city,
    contentLayoutId = R.layout.main_fragment,
    type = ToolbarType.CITY
) {

    companion object {
        const val MAIN_RESULT_KEY = "com.sdimosikvip.weather.screens.main.MAIN_RESULT_KEY"

        fun makeBundle(locationWeather: WeatherLocationInfo): Bundle =
            bundleOf(MAIN_RESULT_KEY to locationWeather)
    }

    private fun setupFromArguments() {

        if (arguments != null) {
            requireArguments().getParcelable<WeatherLocationInfo>(MAIN_RESULT_KEY)?.let { locationWeather ->
                Timber.tag("mydebug").d("return Args")
                viewModel.fetchNewLocation(ResultResponse.success(locationWeather))
            }
        }
    }

    override var binding by autoCleared<MainFragmentBinding>()

    override fun getToolbarLayout(): View = binding.frgMainLayoutToolbar.root

    private val viewModel: MainViewModel by viewModels()

    private lateinit var hourlyAdapter: HourlyAdapter
    private lateinit var dailyAdapter: DailyAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        hourlyAdapter = HourlyAdapter(Glide.with(this))
        dailyAdapter = DailyAdapter(Glide.with(this))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribe()
        setupFromArguments()
    }

    override fun onStop() {
        val scrollState = binding.nestedScrollView.scrollY
        viewModel.fetchStateScroll(scrollState)
        super.onStop()
    }

    private fun setupViews() {
        binding.frgMainHourlyTempRv.setup(hourlyAdapter, R.layout.item_hourly_weather)
        binding.frgMainDailyTempRv.setup(dailyAdapter, R.layout.item_daily_weather)

        binding.swipeContainer.setColorSchemeColors(
            getColorHelper(requireContext(), R.color.color_gradient_start),
            getColorHelper(requireContext(), R.color.color_gradient_center),
            getColorHelper(requireContext(), R.color.color_gradient_end)
        )

        binding.swipeContainer.setOnRefreshListener {
            Timber.tag("mydebug").d("Refresh container")
            viewModel.fetchLocation()
        }

        binding.frgMainLayoutToolbar.includeToolbarCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_manageCitiesFragment)
        }
    }

    private fun subscribe() {

        viewModel.location.observe(viewLifecycleOwner) {
            Timber.tag("mydebug").d("Location observe. Status: ${it.status}")
            when (it.status) {

                ResultResponse.Status.LOADING -> {
                    binding.swipeContainer.isRefreshing = true
                }

                ResultResponse.Status.SUCCESS -> {
                    with(it.data!!) {
                        binding.frgMainLayoutToolbar.includeToolbarTitle.text = name
                        viewModel.fetchWeather(this)
                    }
                    binding.swipeContainer.isRefreshing = false
                }

                ResultResponse.Status.ERROR -> {
                    showError(binding.root, "Some problems")
                    binding.swipeContainer.isRefreshing = false
                }

            }
        }

        viewModel.weather.observe(viewLifecycleOwner) { resultResponse ->
            Timber.tag("mydebug").d("Weather observe. Status: ${resultResponse.status}")
            when (resultResponse.status) {

                ResultResponse.Status.LOADING -> {
                    binding.frgMainHourlyTempRv.showShimmer()
                    binding.frgMainDailyTempRv.showShimmer()
                    binding.swipeContainer.isRefreshing = true
                }

                ResultResponse.Status.SUCCESS -> {
                    with(resultResponse.data!!) {
                        hourlyAdapter.bindData(hourly.toHourWeatherList(timezone))
                        dailyAdapter.bindData(daily.toDayWeatherList(timezone))

                        with(binding) {
                            frgMainCurrentTempNum.text =
                                TransformUtils.convertKelvinToCelsius(current.temp).toString()
                            frgMainCurrentTempType.text = TransformUtils.celsius
                            frgMainCurrentTempDescription.text =
                                current.weatherInfo[0].description
                            frgMainCurrentData.text =
                                TransformUtils.formatMonthDayWeek(current.dt, timezone)

                            frgMainFeelsLike.text =
                                TransformUtils.formatFeelsLikeDef(current.feelsLike)
                            frgMainHumidity.text =
                                TransformUtils.formatHumidityDef(current.humidity)
                            frgMainWind.text =
                                formatWindDef(current.windSpeed)
                            frgMainClouds.text =
                                TransformUtils.formatCloudsDef(current.clouds)
                            frgMainVisibility.text =
                                TransformUtils.formatVisibilityDef(current.visibility)
                            frgMainAirPressure.text =
                                TransformUtils.formatAirPressureDef(current.pressure)

                            frgMainSunrise.text =
                                TransformUtils.formatHoursAndMinutes(current.sunrise, timezone)
                            frgMainSunset.text =
                                TransformUtils.formatHoursAndMinutes(current.sunset, timezone)
                        }
                    }
                    binding.swipeContainer.visibility = View.VISIBLE
                    binding.frgMainHourlyTempRv.hideShimmer()
                    binding.frgMainDailyTempRv.hideShimmer()
                    binding.swipeContainer.isRefreshing = false
                }

                ResultResponse.Status.ERROR -> {
                    showError(binding.root, "Network error")
                    binding.swipeContainer.visibility = View.GONE
                    binding.frgMainHourlyTempRv.hideShimmer()
                    binding.frgMainDailyTempRv.hideShimmer()
                    binding.swipeContainer.isRefreshing = false
                }
            }
        }

        viewModel.stateScroll.observe(viewLifecycleOwner) { scrollState ->
            binding.nestedScrollView.scrollY = scrollState
        }
    }

}
