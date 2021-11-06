package com.sdimosikvip.weather.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.MainFragmentBinding
import com.sdimosikvip.weather.utils.autoCleared
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    private var binding by autoCleared<MainFragmentBinding>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

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

        binding.btnCity.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_manageCitiesFragment)
        }

        binding.btnSetting.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_settingFragment)
        }
    }

    private fun makeBundle(): Bundle = bundleOf(MAIN_KEY to "")

    companion object {
        const val MAIN_KEY = "com.sdimosikvip.weather.screens.main.SERVICE_TITLE_KEY"
    }
}