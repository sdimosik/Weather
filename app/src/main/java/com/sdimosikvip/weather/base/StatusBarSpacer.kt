package com.sdimosikvip.weather.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View

@SuppressLint("ObsoleteSdkInt")
class StatusBarSpacer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private var statusHeight: Int = 60

    init {
        if (context is Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            context.window.decorView.setOnApplyWindowInsetsListener { _, insets ->
                statusHeight = insets.systemWindowInsetTop
                requestLayout()
                insets
            }
            context.window.decorView.requestApplyInsets()
        } else statusHeight = resources.getDimensionPixelSize(
            resources.getIdentifier("status_bar_height", "dimen", "android")
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) =
        setMeasuredDimension(0, statusHeight)
}
