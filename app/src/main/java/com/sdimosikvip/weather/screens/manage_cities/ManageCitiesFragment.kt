package com.sdimosikvip.weather.screens.manage_cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.ManageCitiesFragmentBinding
import com.sdimosikvip.weather.utils.autoCleared
import dagger.Lazy
import javax.inject.Inject

class ManageCitiesFragment : Fragment(R.layout.manage_cities_fragment) {

    private var binding by autoCleared<ManageCitiesFragmentBinding>()

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    val viewModel: ManageCitiesViewModel by viewModels { viewModelFactory.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ManageCitiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}