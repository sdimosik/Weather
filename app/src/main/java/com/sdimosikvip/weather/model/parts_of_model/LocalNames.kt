package com.sdimosikvip.weather.model.parts_of_model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalNames(
    @field:SerializedName("ascii")
    val ascii: String,
    @field:SerializedName("en")
    val en: String,
    @field:SerializedName("feature_name")
    val featureName: String
) : Parcelable
