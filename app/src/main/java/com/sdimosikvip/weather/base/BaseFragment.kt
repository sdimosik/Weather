package com.sdimosikvip.weather.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected abstract val binding: ViewBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
}