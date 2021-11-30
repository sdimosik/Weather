package com.sdimosikvip.weather.model.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sdimosikvip.weather.base.BaseModel
import com.sdimosikvip.weather.model.parts_of_model.LocalNames
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationWeather(
    override val id: Long = 0,

    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("state")
    val state: String?
) : BaseModel(), Parcelable {
    override fun isContentEqual(other: BaseModel): Boolean =
        other is LocationWeather && id == other.id && lat == other.lat
                && lon == other.lon && localNames == other.localNames
                && name == other.name && state == other.state
}
