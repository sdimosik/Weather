package com.sdimosikvip.weather.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    protected abstract val binding: ViewBinding
}
