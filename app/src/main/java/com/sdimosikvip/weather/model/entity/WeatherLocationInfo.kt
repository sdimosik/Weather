package com.sdimosikvip.weather.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sdimosikvip.weather.base.BaseModel
import com.sdimosikvip.weather.model.parts_of_model.LocalNames
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = WeatherLocationInfo.TABLE_NAME)
data class WeatherLocationInfo(
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
) : Parcelable {

    @IgnoredOnParcel
    @field:SerializedName("unique_id")
    @PrimaryKey(autoGenerate = true)
    var uId: Long = 0

    companion object {
        const val TABLE_NAME = "coordinate_table"
    }

}
