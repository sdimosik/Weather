package com.sdimosikvip.weather.screens.manage_cities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.base.ToolbarFragment
import com.sdimosikvip.weather.databinding.ManageCitiesFragmentBinding
import com.sdimosikvip.weather.extensions.setup
import com.sdimosikvip.weather.mapper.toLocationWeatherList
import com.sdimosikvip.weather.mapper.toWeatherLocationInfo
import com.sdimosikvip.weather.model.domain.LocationWeather
import com.sdimosikvip.weather.model.entity.WeatherLocationInfo
import com.sdimosikvip.weather.screens.main.MainFragment
import com.sdimosikvip.weather.screens.manage_cities.adapter.LocationAdapter
import com.sdimosikvip.weather.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ManageCitiesFragment : ToolbarFragment(
    titleResource = R.string.manage_cities,
    contentLayoutId = R.layout.manage_cities_fragment,
    type = ToolbarType.BACK
) {

    companion object {
        const val MANAGE_CITIES_KEY =
            "com.sdimosikvip.weather.screens.manage_cities.MANAGE_CITIES_KEY"

        fun makeBundle(locationWeather: WeatherLocationInfo): Bundle =
            bundleOf(MANAGE_CITIES_KEY to locationWeather)
    }

    override fun getToolbarLayout(): View = binding.frgManageCitiesLayoutToolbar.root

    override var binding by autoCleared<ManageCitiesFragmentBinding>()

    private val viewModel: ManageCitiesViewModel by viewModels()

    private lateinit var locationAdapter: LocationAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        locationAdapter = LocationAdapter {
            findNavController().navigate(
                R.id.action_manageCitiesFragment_to_mainFragment,
                MainFragment.makeBundle(it.toWeatherLocationInfo())
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ManageCitiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        subscribe()
    }

    private fun setupViews() {
        binding.listPickedLocationListShimmer.setup(locationAdapter, R.layout.item_location_weather)

        binding.fabAddFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_manageCitiesFragment_to_searchBottomSheetDialogFragment)
        }
    }

    private fun subscribe() {

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<LocationWeather>(
            MANAGE_CITIES_KEY
        )?.observe(viewLifecycleOwner) {
            if (it != null) viewModel.saveAndUpdateWeatherLocations(it.toWeatherLocationInfo())
        }


        viewModel.weatherLocations.observe(viewLifecycleOwner) { resultResponse ->
            Timber.tag("mydebug").d("List local location. Status: ${resultResponse.status}")
            when (resultResponse.status) {

                ResultResponse.Status.LOADING -> {
                    Timber.tag("mydebug").i("loading")
                }

                ResultResponse.Status.SUCCESS -> {

                    if (resultResponse.data != null && resultResponse.data.isNotEmpty()) {
                        with(resultResponse.data) {
                            locationAdapter.bindData(this.toLocationWeatherList())
                        }
                        binding.listPickedLocationListShimmer.visibility = View.VISIBLE
                        binding.emptyCitiesList.visibility = View.GONE
                    } else {
                        binding.listPickedLocationListShimmer.visibility = View.GONE
                        binding.emptyCitiesList.visibility = View.VISIBLE
                    }
                }

                ResultResponse.Status.ERROR -> {
                    Timber.tag("mydebug").e(resultResponse.message)
                }
            }

        }
    }

}
