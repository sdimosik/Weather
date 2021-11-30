package com.sdimosikvip.weather.base

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import com.sdimosikvip.weather.R
import com.sdimosikvip.weather.databinding.IncludeToolbarBinding
import com.sdimosikvip.weather.utils.getColorHelper

abstract class ToolbarFragment constructor(
    private val type: ToolbarType,
    @StringRes private val titleResource: Int,
    @LayoutRes contentLayoutId: Int
) : BaseFragment(contentLayoutId) {

    private var _layoutToolbarBinding: IncludeToolbarBinding? = null
    private val layoutToolbarBinding get() = _layoutToolbarBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()
        super.onViewCreated(view, savedInstanceState)
    }

    fun setToolbarTitle(text: String) {
        layoutToolbarBinding.includeToolbarTitle.text = text
    }

    protected abstract fun getToolbarLayout(): View

    private fun setToolbar() {
        _layoutToolbarBinding = IncludeToolbarBinding.bind(getToolbarLayout())
        when (type) {
            ToolbarType.EMPTY -> {
                layoutToolbarBinding.includeToolbarIvButton.visibility = View.GONE
                layoutToolbarBinding.includeToolbarCityButton.visibility = View.GONE
            }
            ToolbarType.CITY -> {
                layoutToolbarBinding.includeToolbarIvButton.visibility = View.GONE
                layoutToolbarBinding.includeToolbarCityButton.visibility = View.VISIBLE
                layoutToolbarBinding.includeToolbarIvButton.setImageResource(type.id)
                layoutToolbarBinding.includeToolbarTitle.setTextColor(
                    getColorHelper(
                        requireContext(),
                        R.color.color_secondary
                    )
                )
            }
            ToolbarType.BACK -> {
                layoutToolbarBinding.includeToolbarIvButton.visibility = View.VISIBLE
                layoutToolbarBinding.includeToolbarCityButton.visibility = View.GONE
                layoutToolbarBinding.includeToolbarIvButton.setImageResource(type.id)
                layoutToolbarBinding.includeToolbarTitle.setTextColor(
                    getColorHelper(
                        requireContext(),
                        R.color.background_primary
                    )
                )
                layoutToolbarBinding.includeToolbarIvButton.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
        if (titleResource != 0) {
            layoutToolbarBinding.includeToolbarTitle.setText(titleResource)
        }
    }

    enum class ToolbarType(@DrawableRes val id: Int) {
        CITY(R.drawable.ic_baseline_location_city_24),
        BACK(R.drawable.ic_arrow_left_24),
        EMPTY(0)
    }
}
