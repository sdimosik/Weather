package com.sdimosikvip.weather.utils

import kotlin.math.pow
import kotlin.math.roundToInt

object Extension {
    fun Double.roundTo(numFractionDigits: Int): Double {
        val factor = 10.0.pow(numFractionDigits.toDouble())
        return (this * factor).roundToInt() / factor
    }
}