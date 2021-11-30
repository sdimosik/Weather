package com.sdimosikvip.weather.screens.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.api.ResultResponse
import com.sdimosikvip.weather.databinding.SearchBottomSheetDialogFragmentBinding
import com.sdimosikvip.weather.extensions.setup
import com.sdimosikvip.weather.mapper.toLocationWeatherList
import com.sdimosikvip.weather.screens.manage_cities.ManageCitiesFragment
import com.sdimosikvip.weather.screens.search.adapter.SearchLocationAdapter
import com.sdimosikvip.weather.utils.LocationUtils.REQUEST_CODE_LOCATION_PERMISSION
import com.sdimosikvip.weather.utils.autoCleared
import com.sdimosikvip.weather.utils.hasLocationPermissions
import com.sdimosikvip.weather.utils.hasPermission
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber


@AndroidEntryPoint
class SearchBottomSheetDialogFragment : BottomSheetDialogFragment(),
    EasyPermissions.PermissionCallbacks {

    var binding by autoCleared<SearchBottomSheetDialogFragmentBinding>()

    private val viewModel: SearchViewModel by viewModels()

    private lateinit var searchLocationAdapter: SearchLocationAdapter

    // The Fused Location Provider provides access to location APIs.
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    // Allows class to cancel the location request if it exits the activity.
    // Typically, you use one cancellation source per lifecycle.
    private var cancellationTokenSource = CancellationTokenSource()

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    companion object {
        const val SEARCH_KEY = "com.sdimosikvip.weather.screens.search.SEARCH_KEY"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        searchLocationAdapter =
            SearchLocationAdapter {
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    ManageCitiesFragment.MANAGE_CITIES_KEY,
                    it
                )
                findNavController().popBackStack()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchBottomSheetDialogFragmentBinding.bind(
            inflater.inflate(
                R.layout.search_bottom_sheet_dialog_fragment,
                container,
                false
            )
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.root.layoutParams.height = getScreenHeight(this.requireActivity()) - 300
        val view1: View = this.requireView()
        view1.post {
            val parent = view1.parent as View
            val params =
                parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            val bottomSheetBehavior = (behavior as BottomSheetBehavior<*>?)!!
            bottomSheetBehavior.peekHeight = view1.measuredHeight
        }

        super.onViewCreated(view1, savedInstanceState)

        setupViews()
        subscribe()
    }

    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }

    private fun setupViews() {

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    viewModel.updateLocationsByName(query)
                    binding.search.clearFocus()
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.searchListShimmer.setup(
            searchLocationAdapter,
            R.layout.item_search_location_weather
        )

        binding.addCurrentLocation.setOnClickListener {
            requestPermissions()
        }
    }

    private fun subscribe() {
        viewModel.searchLocationList.observe(viewLifecycleOwner) {
            Timber.tag("mydebug").d("List search location. Status: ${it.status}")

            when (it.status) {

                ResultResponse.Status.LOADING -> {
                    Timber.tag("mydebug").i("Loading")
                }

                ResultResponse.Status.SUCCESS -> {

                    if (it.data != null && it.data.isNotEmpty()) {
                        with(it.data) {
                            searchLocationAdapter.bindData(this.toLocationWeatherList())
                        }
                        binding.searchListShimmer.visibility = View.VISIBLE
                        binding.emptyView.visibility = View.GONE
                    } else {
                        binding.searchListShimmer.visibility = View.GONE
                        binding.emptyView.visibility = View.VISIBLE
                    }
                }

                ResultResponse.Status.ERROR -> {
                    Timber.tag("mydebug").e(it.message)
                }
            }
        }
    }

    private fun getScreenHeight(requireActivity: FragmentActivity): Int {
        return requireActivity.window.decorView.height
    }

    @SuppressLint("MissingPermission")
    private fun requestPermissions() {
        if (hasLocationPermissions(requireContext())) {

            if (requireContext().hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                && requireContext().hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            ) {

                val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                    PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                )

                currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                    if (task.isSuccessful && task.result != null) {
                        viewModel.updateLocationsByCoordinates(
                            task.result.latitude,
                            task.result.longitude
                        )
                    } else {
                        Timber.tag("mydebug").e(task.exception)
                    }
                }
            }

            return
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}
