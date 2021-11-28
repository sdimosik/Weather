package com.sdimosikvip.weather.screens.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.SettingFragmentBinding
import com.sdimosikvip.weather.utils.autoCleared
import dagger.Lazy
import javax.inject.Inject

class SettingFragment : Fragment(R.layout.setting_fragment) {

    private var binding by autoCleared<SettingFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
