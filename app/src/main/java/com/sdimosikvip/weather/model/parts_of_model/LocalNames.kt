package com.sdimosikvip.weather.model.parts_of_model

import com.google.gson.annotations.SerializedName

data class LocalNames(
    @field:SerializedName("ascii")
    val ascii: String,
    @field:SerializedName("ca")
    val ca: String,
    @field:SerializedName("en")
    val en: String,
    @field:SerializedName("feature_name")
    val featureName: String
)
